package com.rtitipco.kafkatofolder.kafkaconfig;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaProducerConfig {

    @Autowired
    KafkaProperties properties;

    @Bean
    public KafkaProducer<String, String> kafkaProducer() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, properties.getRequestTimeout());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        //props.put("value.serializer","org.apache.kafka.cpmmon.serialization.StringSerializer");

        props.put(ProducerConfig.RETRIES_CONFIG, properties.getRetries());
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, properties.getMaxBlock());
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, properties.getMaxInFlight());

      //  props.put(ProducerConfig.CLIENT_ID_CONFIG, properties.getClientId());



       // Thread.currentThread().setContextClassLoader(null);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        return producer;
    }
}