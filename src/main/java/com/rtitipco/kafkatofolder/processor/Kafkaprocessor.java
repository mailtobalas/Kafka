package com.rtitipco.kafkatofolder.processor;

//import com.tesco.rtstipcoreplacement.FolderToKafka;

import com.rtitipco.kafkatofolder.kafkaconfig.KafkaProperties;
//import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Component
public class Kafkaprocessor {
    Logger log = LoggerFactory.getLogger(Kafkaprocessor.class);
    @Autowired
    KafkaProperties properties;

   // @Autowired
   // private KafkaProducer<String, String> kafkaProducer;

    public void PostMessageToKafka(String strmessage)
    {
        /*File lstfile = new File("/Users/balas/Desktop/SourceFiles/");
        String[] fileList = lstfile.list();
        for(String filename:fileList) {

            System.out.println( GetMessage(filename));
            kafkaProducer.send(new ProducerRecord<>("invoices", GetMessage(filename)));*/
      //  kafkaProducer.send(new ProducerRecord<>("invoices", strmessage));

log.info("completed.........");


    }

    public String  GetMessage(String filename)
    {
        String  filecontent="";

        File file = new File(
                "/Users/balas/Desktop/SourceFiles/" + filename);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);

                line = br.readLine();
            }
            filecontent = sb.toString();
            ;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return filecontent;

    }
}
