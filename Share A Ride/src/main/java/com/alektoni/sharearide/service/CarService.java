package com.alektoni.sharearide.service;

import com.alektoni.sharearide.model.Car;


import java.util.ArrayList;
import java.util.Optional;

public interface CarService {
    Car save(Car car);

    Optional<Car> findById(Long aLong);

    void deleteById(Long aLong);

    ArrayList<Car> getAll();

    ArrayList<Car> findByDriverId(Long driverId);

    Optional<Car> getActiveCarForUserWithId(Long userId);

}
