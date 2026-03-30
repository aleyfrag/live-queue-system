package com.myProject.queue_service.controller;


import com.myProject.queue_service.dto.ApiResponse;
import com.myProject.queue_service.service.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private static final Logger log = LoggerFactory.getLogger(QueueController.class);

    @Autowired
    QueueService queueService;

    @GetMapping("/position")
    public ResponseEntity<?> getPosition(@RequestParam String email) {
        System.out.println("get hit");
        try {
            int pos = queueService.getPosition(email);

            if (pos == -1) {
                return ResponseEntity.ok(Map.of(
                        "success", false,
                        "message", "User not in queue",
                        "position", -1
                ));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "User found",
                    "position", pos
            ));

        } catch (Exception e) {
            System.out.println(e.getMessage());

            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "Internal Server Error"
            ));
        }
    }


    @PostMapping("/serve")
    public String serveUser() {
        return queueService.serveUser();
    }


}
