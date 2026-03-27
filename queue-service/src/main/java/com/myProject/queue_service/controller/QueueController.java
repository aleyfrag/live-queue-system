package com.myProject.queue_service.controller;


import com.myProject.queue_service.dto.ApiResponse;
import com.myProject.queue_service.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private static final Logger log = LoggerFactory.getLogger(QueueController.class);

    @Autowired
    QueueService queueService;

    public ApiResponse<Integer> addUser(@RequestParam String msg){



    }
}
