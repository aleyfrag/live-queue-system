package com.myProject.queue_service.service;


import com.myProject.queue_service.controller.QueueController;
import com.myProject.queue_service.entity.QueueEntity;
import com.myProject.queue_service.exception.QueueEmptyException;
import com.myProject.queue_service.exception.UserAlreadyExistException;
import com.myProject.queue_service.exception.UserNotFoundException;
import com.myProject.queue_service.repository.QueueRepository;
import io.netty.util.internal.PriorityQueue;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
public class QueueService {

    private static final Logger log = LoggerFactory.getLogger(QueueService.class);

    private  Queue<String> queue = new LinkedList<>();



    public synchronized void addUser(String email)  {

        if(queue.contains(email)){
            log.error("User already in queue: {}",email);

        }

        queue.add(email);
        System.out.println("user list " +queue);
        log.info("User added in queue: {}",email);

    }


    public synchronized String serveUser(){
        if(queue.isEmpty()){
            return "queue is empty";
        }

        String servedUser = queue.poll();
        log.info("User served : {}",servedUser);
        return  servedUser;
    }

    public synchronized  int getPosition(String email){
        int position = 1;

        for (String user : queue) {
            if (user.equals(email)) {
                return position;
            }
            position++;
        }

        return -1;
    }



}
