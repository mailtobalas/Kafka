
package com.rtitipco.kafkatofolder.kafkaconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration file to read the kafka configuration for topics.
 *
 */

@Component
@ConfigurationProperties(prefix = "kafkaproperties.kafka")

public class KafkaProperties {

    String topic;
    String bootstrapServers;
    String groupId;
    String autoOffsetReset;
    String keyDeserializer;
    String valueDeserializer;
    String maxPollRecords;
    String maxPartitionFetchBytes;
    String heartbeatInterval;
    String maxPollInterval;
    String sessionTimeout;
    String requestTimeout;
    String retries;
    String maxBlock;
    String maxInFlight;

    public String getTopic() {
        return topic;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public String getMaxPollRecords() {
        return maxPollRecords;
    }

    public String getMaxPartitionFetchBytes() {
        return maxPartitionFetchBytes;
    }

    public String getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public String getMaxPollInterval() {
        return maxPollInterval;
    }

    public String getSessionTimeout() {
        return sessionTimeout;
    }

    public String getRequestTimeout() {
        return requestTimeout;
    }

    public String getRetries() {
        return retries;
    }

    public String getMaxBlock() {
        return maxBlock;
    }

    public String getMaxInFlight() {
        return maxInFlight;
    }



    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public void setMaxPollRecords(String maxPollRecords) {
        this.maxPollRecords = maxPollRecords;
    }

    public void setMaxPartitionFetchBytes(String maxPartitionFetchBytes) {
        this.maxPartitionFetchBytes = maxPartitionFetchBytes;
    }

    public void setHeartbeatInterval(String heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public void setMaxPollInterval(String maxPollInterval) {
        this.maxPollInterval = maxPollInterval;
    }

    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public void setRequestTimeout(String requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public void setRetries(String retries) {
        this.retries = retries;
    }

    public void setMaxBlock(String maxBlock) {
        this.maxBlock = maxBlock;
    }

    public void setMaxInFlight(String maxInFlight) {
        this.maxInFlight = maxInFlight;
    }




}
