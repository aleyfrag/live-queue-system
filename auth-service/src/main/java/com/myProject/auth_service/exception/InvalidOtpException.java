package com.myProject.auth_service.exception;

public class InvalidOtpException extends RuntimeException{

    public InvalidOtpException(String msg){
        super(msg);
    }
}
