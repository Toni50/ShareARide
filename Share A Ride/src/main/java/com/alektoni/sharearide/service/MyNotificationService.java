package com.alektoni.sharearide.service;



import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.MyNotification;
import com.alektoni.sharearide.model.RideRequest;
import com.alektoni.sharearide.model.vm.MyPage;

import java.util.ArrayList;
import java.util.Optional;

public interface MyNotificationService {

    MyNotification save(RideRequest rideRequest);

    Optional<MyNotification> findById(Long aLong);

    void deleteById(Long aLong);

    MyPage<MyNotification> findByToUserId(Long userId, int page, int pageSize);

    ArrayList<MyNotification> getAll();

}
