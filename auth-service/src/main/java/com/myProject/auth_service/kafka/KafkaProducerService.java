package com.myProject.auth_service.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "queue-topic";

    public void sendMessage(String email) {
        log.info("sending msg to kafka");

        try {
            log.info("Sending message to Kafka topic: {} for email: {}", TOPIC, email);

            kafkaTemplate.send(TOPIC, email);

            log.info("Message successfully sent to Kafka for email: {}", email);

            System.out.println(" Sent to Kafka: " + email);
        } catch (Exception e){

            log.error("Failed to send message to Kafka for email: {}", email, e);
            throw new KafkaException("kafka publisher failed");
        }
    }




}
