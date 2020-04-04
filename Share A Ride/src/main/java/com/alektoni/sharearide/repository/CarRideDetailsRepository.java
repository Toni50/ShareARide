package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.CarRideDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface CarRideDetailsRepository extends CrudRepository<CarRideDetails,Long> {

}
