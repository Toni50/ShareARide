package com.alektoni.sharearide.service;



import com.alektoni.sharearide.model.RideRequest;
import com.alektoni.sharearide.model.vm.MyPage;

import java.util.ArrayList;
import java.util.Optional;

public interface RideRequestService {

    RideRequest save(RideRequest rideRequest);

    Optional<RideRequest> findById(Long aLong);

    MyPage<RideRequest> getUpcomingRidesForUser(Long id, int page, int pageSize);

    MyPage<RideRequest> getHistoryRidesForUser(Long id,int page,int pageSize);

    MyPage<RideRequest> getRideRequestToUserId(Long id,int page,int pageSize);

    void deleteById(Long aLong);

    ArrayList<RideRequest> getAll();



}
