package com.alektoni.sharearide.service.impl;


import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.CarRideDetails;
import com.alektoni.sharearide.repository.CarRepository;
import com.alektoni.sharearide.repository.CarRideDetailsRepository;
import com.alektoni.sharearide.service.CarRideDetailsService;
import com.alektoni.sharearide.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;


@Service
public class CarRideDetailsServiceImpl implements CarRideDetailsService {


    @Autowired
    CarRideDetailsRepository carRideDetailsRepository;


    @Override
    public CarRideDetails save(CarRideDetails carRideDetails) {
        return carRideDetailsRepository.save(carRideDetails);
    }

    @Override
    public Optional<CarRideDetails> findById(Long rideId) {
        return carRideDetailsRepository.findById(rideId);
    }


    @Override
    public void deleteById(Long rideId) {
        carRideDetailsRepository.deleteById(rideId);
    }

    @Override
    public ArrayList<CarRideDetails> getAll() {
        Iterator<CarRideDetails> it = carRideDetailsRepository.findAll().iterator();
        ArrayList<CarRideDetails> tmp = new ArrayList<>();
        while (it.hasNext()) {
            tmp.add(it.next());
        }
        return tmp;
    }




}
