package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.Ride;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;


public interface CarRepository extends CrudRepository<Car,Long> {


    @Query("SELECT car FROM Car car WHERE car.driver.userId =  ?1")
    ArrayList<Car> findByDriverId(Long driverId);

    @Query("SELECT car FROM Car car WHERE car.active = true ")
    Optional<Car> getActiveCarForUserWithId(Long userId);
}
