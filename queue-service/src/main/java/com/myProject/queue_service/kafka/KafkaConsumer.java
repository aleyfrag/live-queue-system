package com.myProject.queue_service.kafka;

import com.myProject.queue_service.exception.UserAlreadyExistException;
import com.myProject.queue_service.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    QueueService queueService;

    @KafkaListener(topics = "queue-topic")
    public void consume(String email)  {
        System.out.println("Received from Kafka: " + email);
        queueService.addUser(email);

    }
}
