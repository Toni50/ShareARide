package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.MyNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MyNotificationRepository extends CrudRepository<MyNotification,Long> {

    Page<MyNotification> findByToUserId(Long userId, Pageable pageable);
}
