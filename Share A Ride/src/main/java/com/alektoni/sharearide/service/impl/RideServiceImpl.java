package com.alektoni.sharearide.service.impl;

import com.alektoni.sharearide.model.Review;
import com.alektoni.sharearide.model.Ride;
import com.alektoni.sharearide.repository.CarRepository;
import com.alektoni.sharearide.repository.RideRepository;
import com.alektoni.sharearide.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RideServiceImpl implements RideService {

    @Autowired
    RideRepository rideRepository;


    @Override
    public Ride save(Ride ride) {
       return rideRepository.save(ride);
    }

    @Override
    public Optional<Ride> findById(Long rideId) {
        return rideRepository.findById(rideId);
    }

    @Override
    public void deleteById(Long rideId) {
        rideRepository.deleteById(rideId);
    }

    @Override
    public ArrayList<Ride> getAll() {
        Iterator<Ride> it = rideRepository.findAll().iterator();
        ArrayList<Ride> tmp = new ArrayList<>();
        while (it.hasNext()){
            tmp.add(it.next());
        }
        return tmp;
    }

    @Override
    public List<Ride> findRides(Date startTime, Date endTime) {
        return rideRepository.findRides(startTime,endTime);
    }
}
