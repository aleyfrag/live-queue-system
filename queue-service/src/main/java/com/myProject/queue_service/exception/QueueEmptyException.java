package com.myProject.queue_service.exception;

public class QueueEmptyException extends Exception{

    public QueueEmptyException(String msg){
        super(msg);
    }
}
