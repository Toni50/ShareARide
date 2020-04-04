package com.alektoni.sharearide.service.impl;


import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.repository.CarRepository;
import com.alektoni.sharearide.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {


    @Autowired
    CarRepository carRepository;


    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> findById(Long rideId) {
        return carRepository.findById(rideId);
    }

    @Override
    public ArrayList<Car> findByDriverId(Long driverId){
        return carRepository.findByDriverId(driverId);
    }

    @Override
    public Optional<Car> getActiveCarForUserWithId(Long userId) {
        return carRepository.getActiveCarForUserWithId(userId);
    }


    @Override
    public void deleteById(Long rideId) {
        carRepository.deleteById(rideId);
    }

    @Override
    public ArrayList<Car> getAll() {
        Iterator<Car> it = carRepository.findAll().iterator();
        ArrayList<Car> tmp = new ArrayList<>();
        while (it.hasNext()) {
            tmp.add(it.next());
        }
        return tmp;
    }


  /*  @Override
    public ArrayList<Car> findAllCarsWithDriverId(Long userId) {
       *//* Iterator<Car> it = carRepository.findAll().iterator();
        ArrayList<Car> tmp = new ArrayList<>();
        while (it.hasNext()) {
            Car car = it.next();
            if (car.getDriver().getUserId().equals(userId)) {
                tmp.add(car);
            }
        }
        return tmp;*//*

        ArrayList<Car> tmp = carRepository.findAllCarsWithDriverId(userId);


        return tmp;

    }*/

}
