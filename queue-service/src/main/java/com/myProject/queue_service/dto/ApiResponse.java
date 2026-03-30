package com.myProject.queue_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.shaded.com.google.protobuf.Api;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String message;
    private boolean success;
    private int position;

    public ApiResponse(String message,boolean success){
        this.message = message;
        this.success= success;
    }

    



}
