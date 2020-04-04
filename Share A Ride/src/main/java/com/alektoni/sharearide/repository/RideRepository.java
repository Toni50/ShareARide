package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.Review;
import com.alektoni.sharearide.model.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RideRepository extends CrudRepository<Ride,Long> {


    //@Query("SELECT rev FROM Review rev join Ride ride on rev.rideId = ride.rideId where ride.carRideDetails.driverId = ?1")
    /*
    ride.startTime = 2020 01 01 13:00
    starttime = 2020 01 01 00:00
    endTime= 2020 01 01 23:59
    */

    @Query("SELECT ride FROM Ride ride WHERE ride.startTime >  ?1 and ride.startTime < ?2")
    List<Ride> findRides(Date startTime, Date endTime);

}
