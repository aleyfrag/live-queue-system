    package com.myProject.auth_service.dto;


    import lombok.AllArgsConstructor;
    import lombok.Data;

    @Data
    @AllArgsConstructor
    public class SendOtpRequest {

        private String email;

    }
