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


    public KafkaProducer<String, String> kafkaProducer() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, properties.getRequestTimeout());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

//        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer","org.apache.kafka.cpmmon.serialization.StringSerializer");

        props.put(ProducerConfig.RETRIES_CONFIG, properties.getRetries());
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, properties.getMaxBlock());
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, properties.getMaxInFlight());

       // props.put(ProducerConfig.CLIENT_ID_CONFIG, properties.getClientId());


       // props.put("bootstrap.servers", "localhost:9092");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 163894);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                StringSerializer.class.getName());
       props.put("value.serializer",
                StringSerializer.class.getName());

       // Thread.currentThread().setContextClassLoader(null);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        return producer;
    }
}