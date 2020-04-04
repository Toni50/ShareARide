package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.Review;
import com.alektoni.sharearide.model.RideRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {


    @Query("SELECT rev FROM Review rev join Ride ride on rev.rideId = ride.rideId where ride.carRideDetails.driver.userId = ?1")
    List<Review> findAllReviewLeftForUserWithId(Long userId);


    @Query("SELECT rev FROM Review rev join Ride ride on rev.rideId = ride.rideId where ride.carRideDetails.driver.userId = ?1 " +
            "and rev.rideId = ?2")
    Review getReviewByUserForRide(Long userId, Long rideId);
}
