package com.alektoni.sharearide.service;

import com.alektoni.sharearide.model.Review;
import com.alektoni.sharearide.model.Ride;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RideService {

    Ride save(Ride ride);

    Optional<Ride> findById(Long aLong);

    void deleteById(Long aLong);

    ArrayList<Ride> getAll();

    List<Ride> findRides(Date startTime, Date endTime);
}




