package com.alektoni.sharearide.service.impl;


import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.MyNotification;
import com.alektoni.sharearide.model.RideRequest;
import com.alektoni.sharearide.model.User;
import com.alektoni.sharearide.model.vm.MyPage;
import com.alektoni.sharearide.repository.MyNotificationRepository;
import com.alektoni.sharearide.service.MyNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@Service
public class MyNotificationServiceImpl  implements MyNotificationService {


    @Autowired
    MyNotificationRepository myNotificationRepository;


    @Override
    public MyNotification save(RideRequest rideRequest) {
        User driver = rideRequest.getRide().getCarRideDetails().getDriver();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String timeDate = simpleDateFormat.format( rideRequest.getRide().getStartTime() );


        MyNotification myNotification = new MyNotification(
                driver,
                rideRequest.getRequestByUser().getUserId(),
                 rideRequest.getResponseStatus().name().toLowerCase() + " the ride request from " +
                        rideRequest.getRide().getRideRoute().getLocations().get(0).getLocationCode()
                                .replace("&", " ")
                        + " to " +
                        rideRequest.getRide().getRideRoute().getLocations().get(
                                rideRequest.getRide().getRideRoute().getLocations().size() - 1).getLocationCode()
                                .replace("&", " ")
                        + " on " +timeDate,
                new Date()
        );
        return myNotificationRepository.save(myNotification);
    }

    @Override
    public Optional<MyNotification> findById(Long rideId) {
        return myNotificationRepository.findById(rideId);
    }

    @Override
    public void deleteById(Long rideId) {
        myNotificationRepository.deleteById(rideId);
    }

    @Override
    public MyPage<MyNotification> findByToUserId(Long userId,int page,int pageSize) {
        org.springframework.data.domain.Page<MyNotification> result =
                myNotificationRepository.findByToUserId(userId,
                        PageRequest.of(page, pageSize, Sort.by("timeDate").descending())
                );
        return new MyPage<MyNotification>(page,pageSize,result.getTotalPages(),result.getContent());
    }


    @Override
    public ArrayList<MyNotification> getAll() {
        Iterator<MyNotification> it = myNotificationRepository.findAll().iterator();
        ArrayList<MyNotification> tmp = new ArrayList<>();
        while (it.hasNext()) {
            tmp.add(it.next());
        }
        return tmp;
    }


}
