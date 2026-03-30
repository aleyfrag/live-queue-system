package com.myProject.queue_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class QueueServiceApplication
{
    public static void main( String[] args ) {SpringApplication.run(QueueServiceApplication.class, args);}
}
