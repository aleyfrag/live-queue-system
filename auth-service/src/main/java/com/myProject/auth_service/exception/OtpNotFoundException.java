package com.myProject.auth_service.exception;

public class OtpNotFoundException extends RuntimeException{

    public OtpNotFoundException(String msg){
        super(msg);
    }
}
