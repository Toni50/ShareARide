package com.alektoni.sharearide.service.impl;

import com.alektoni.sharearide.model. RideRequest;
import com.alektoni.sharearide.model.vm.MyPage;
import com.alektoni.sharearide.repository.RideRequestRepository;
import com.alektoni.sharearide.service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RideRequestServiceImpl  implements RideRequestService {

    @Autowired
    RideRequestRepository rideRequestRepository;


    @Override
    public RideRequest save( RideRequest rideRequest) {
        return rideRequestRepository.save(rideRequest);
    }

    @Override
    public Optional< RideRequest> findById(Long rideId) {
        return rideRequestRepository.findById(rideId);
    }

    @Override
    public MyPage<RideRequest> getUpcomingRidesForUser(Long id, int page, int pageSize) {
        org.springframework.data.domain.Page<RideRequest> result =
                rideRequestRepository.getUpcomingRidesForUser(id,new Date(),
                        PageRequest.of(page, pageSize, Sort.by("ride.startTime").ascending())
                );
        return new MyPage<RideRequest>(page,pageSize,result.getTotalPages(), result.getContent());
    }

    @Override
    public MyPage<RideRequest> getHistoryRidesForUser(Long id,int page,int pageSize) {
        org.springframework.data.domain.Page<RideRequest> result =
                rideRequestRepository.getHistoryRidesForUser(id,new Date(),
                        PageRequest.of(page, pageSize,Sort.by("timeRequested").descending())
                );
        return new MyPage<RideRequest>(page,pageSize,result.getTotalPages(),result.getContent());
    }

    @Override
    public MyPage<RideRequest> getRideRequestToUserId(Long id,int page,int pageSize) {
        org.springframework.data.domain.Page<RideRequest> result =
                rideRequestRepository.findByRequestToUserId(id,
                        PageRequest.of(page, pageSize,Sort.by("timeRequested").descending())
                );
        return new MyPage<RideRequest>(page,pageSize,result.getTotalPages(),result.getContent());
    }

    @Override
    public void deleteById(Long rideId) {
        rideRequestRepository.deleteById(rideId);
    }

    @Override
    public ArrayList< RideRequest> getAll() {
        Iterator< RideRequest> it = rideRequestRepository.findAll().iterator();
        ArrayList< RideRequest> tmp = new ArrayList<>();
        while (it.hasNext()){
            tmp.add(it.next());
        }
        return tmp;
    }


}
