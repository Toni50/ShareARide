package com.alektoni.sharearide.service;

import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.CarRideDetails;

import java.util.ArrayList;
import java.util.Optional;

public interface CarRideDetailsService {
    CarRideDetails save(CarRideDetails carRideDetails);

    Optional<CarRideDetails> findById(Long aLong);

    void deleteById(Long aLong);

    ArrayList<CarRideDetails> getAll();
}
