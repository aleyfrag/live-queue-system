package com.myProject.queue_service.exception;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
