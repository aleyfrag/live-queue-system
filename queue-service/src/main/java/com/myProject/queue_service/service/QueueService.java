package com.myProject.queue_service.service;

import com.myProject.queue_service.entity.QueueEntity;
import com.myProject.queue_service.exception.QueueEmptyException;
import com.myProject.queue_service.exception.UserAlreadyExistException;
import com.myProject.queue_service.exception.UserNotFoundException;
import com.myProject.queue_service.repository.QueueRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueueService {

    private static final Logger log = LoggerFactory.getLogger(QueueService.class);


    @Autowired
    QueueRepository queueRepository;

    @Transactional
    public int addUser(String email) throws UserAlreadyExistException {
        try{
            log.info("Adding user in queue : {}",email);

            QueueEntity entity = new QueueEntity();
            entity.setEmail(email);

            queueRepository.save(entity);

            log.info("User successfully added in ");

        }catch (DataIntegrityViolationException e){
            log.warn("Duplicate user attempt {}",email);
            throw new UserAlreadyExistException("User already present in queue");

        }

        return getPosition(email);
    }


    public int getPosition(String email) throws UserNotFoundException{

        QueueEntity entity = queueRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User not found: {}", email);
                    return new UserNotFoundException("User not found in queue");
                });
        int position = queueRepository.getPositionById(entity.getId());

        log.info("Position for {} is {}",email,position);

        return position;
    }


    public List<QueueEntity> getAllUsers(){
        return queueRepository.findAllByOrderByIdAsc();
    }

    @Transactional
    public String serveNextUser() throws QueueEmptyException {
        List<QueueEntity> list = queueRepository.findAllByOrderByIdAsc();

        if(list.isEmpty()){
            log.error("Queue is empty");
            throw new QueueEmptyException("Queue is empty");
        }

        QueueEntity firstUser = list.get(0);

        queueRepository.delete(firstUser);

        log.info("User served and removed : {}",firstUser.getEmail());

        log.info("Notification sent to : {}",firstUser.getEmail());

        return firstUser.getEmail();

    }

    @Transactional
    public void removeUser(String email) throws UserNotFoundException{
        log.info("User requested to leave queue: {}", email);

        QueueEntity user = queueRepository.findByEmail(email)
                .orElseThrow(()-> {

                    log.error("User not found: {}", email);

                    return new UserNotFoundException("User not found in queue");
                });

        queueRepository.delete(user);

        log.info("User removed successfully: {}", email);
    }




}
