package io.conduktor.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("info logging started!");

        // create Producer properties
        Properties properties = new Properties();
        // connect to Localhost
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        // set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // connect to Conduktor Platform
//        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
//        properties.setProperty("security.protocol", "SASL_SSL");
//        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=");
//        properties.setProperty("sasl.mechanism", "PLAIN");

        // create Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a Producer Record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_java", "Hello world");

        // send data
        producer.send(producerRecord);

        // flush and close the Producer
        producer.flush();// tell the producer to send all data and block until done --synchronous
        producer.close();
    }
}
