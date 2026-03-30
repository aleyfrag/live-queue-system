package com.myProject.queue_service.repository;

import com.myProject.queue_service.entity.QueueEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface QueueRepository  {

    Optional<QueueEntity> findByEmail(String email);

    @Query("select count(q) from QueueEntity q where q.id<= :id")
    int getPositionById(@Param("id") long id);

    List<QueueEntity> findAllByOrderByIdAsc();
}
