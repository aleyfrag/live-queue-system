package com.myProject.auth_service.exception;


public class KafkaPublishException extends RuntimeException{

    public KafkaPublishException(String msg){
        super(msg);
    }
}
