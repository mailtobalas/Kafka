import fileinput
import os
import random
import shutil
import subprocess
import sys
import urllib.parse
import uuid



REGISTRY = "euwppe127cosbuilddeployacr.azurecr.io"

PROFILES_LIST = [
    'kafkatofolder',
    'kafkatofolders'
]

# key --> starts with
# value --> profile
tree = {
    "kafkatofolder":"kafkatofolder",
    "kafkatofolders":"kafkatofolders",
    "pom.xml": "all",
    "jenkins": "all"
}

def get_profiles(change_set=[]):
    profiles = set()
    for key, val in tree.items():
        for change in change_set:
            if change.startswith(key):
                profiles.add(val)
    # by default build all
    if not profiles:
        profiles.add('all')
    if "all" in profiles:
        profiles = PROFILES_LIST
    return list(profiles)


def gen_build_command(change_set=[]):
    profiles = get_profiles(change_set)
    command = "mvn clean compile findbugs:findbugs pmd:pmd -P {} -Dspring.config.additional-location='file:/config/'".format(
        ",".join(profiles)
    )

    # dont print else job depends on this print statement.
    print(command, end='')
    return profiles

def build_docker(change_set=[], version=None):
    profiles = get_profiles(change_set)
    print(profiles, end='')
    # TODO: uncomment above, remove below line

    #profiles = ['truenorth']
    #createImages(profiles, version)
    # Separate mvn command has been used for profile truenorth to prevent module "duplicated in the reactor" error for cos-scribe-modules
    profiles = ['falcon-injector-consumer','falcon-injector-scribe','falcon-processor','falcon-transformer']
    createImages(profiles, version)
    # get images
    images = subprocess.check_output("docker images", shell=True).decode().split('\n')[1:-1]
    # only picks up cos docker images --> Doesdep not pick base/other images
    cos_images = list(filter(lambda image: image.startswith('falcon'), images))
    cos_images = list(map(lambda image: ':'.join(image.split()[:2]), cos_images))
    cos_dockers = list(map(lambda image: tag(image), cos_images))
    # login first
    subprocess.call(['az', 'acr', 'login', '--name', REGISTRY.split(".", 3)[0]])
    for img in cos_dockers:
        subprocess.check_call("docker push {}".format(img), shell=True)
    print("Skipping clean ecr")
    #TODO clean_ecr()


def tag(image):
    remote_img = "{}/{}".format(REGISTRY, image)
    cmd = "docker tag {} {}".format(image, remote_img)
    print("tagging image {} with {}".format(image, remote_img), flush=True)
    subprocess.check_call(cmd, shell=True)
    return remote_img





def configure_git(adminUser, branch):
    print("configuring git", flush=True)
    git_commands = """
    git config --local user.email "{user}@global.tesco.org"
    echo "User email is as below:"
    git config --local user.email
    git config --local user.name "{user}"
    echo "User name is as below:"
    git config --local user.name
    echo "Checking  into the appropriate branch {branchName}"
    git checkout {branchName}
    git reset --hard origin/{branchName}
    """.format(
        user=adminUser,
        branchName=branch
    )
    subprocess.check_call(git_commands, shell=True)


def release(release_version: str, next_version: str, branch: str, adminUser: str):
    configure_git(adminUser, branch)
    remote = subprocess.check_output("git remote -v | grep origin | awk '{print $2}'", shell=True).decode().split()[0]
    profile =' '.join([str(elem) for elem in PROFILES_LIST])
    releasecommands = """
    mvn -B release:clean release:prepare -DdevelopmentVersion={nextdev_ver}-SNAPSHOT -Dtag=falcon-{release_ver} -DreleaseVersion={release_ver} -Dresume=false -DpushChanges=false -Pall,release
    git status
    git add -u
    git commit -m 'Updating pom for release - {release_ver}'
    sh changelog.sh >> changelog.md
    git add changelog.md
    git commit -m "updated changelog"
    git pull {remote} {branchName}
    git config --global push.default simple
    git push {remote} HEAD:{branchName}
    git fetch --tags {remote}
    echo "Pushing to the remote repo"
    git push {remote} --tags
    """
    releasecommands = releasecommands.format(
        branchName=branch,
        remote=remote,
        user=adminUser,
        nextdev_ver=next_version,
        release_ver=release_version
    )

    subprocess.check_call(releasecommands, shell=True)

