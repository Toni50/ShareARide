package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.RideRequest;
import com.alektoni.sharearide.model.RideRequestResponseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface RideRequestRepository extends CrudRepository<RideRequest,Long> {

    @Query("SELECT req FROM RideRequest req WHERE req.requestByUser.userId = ?1 and req.ride.startTime > ?2")
    Page<RideRequest> getUpcomingRidesForUser(Long id, Date now, Pageable pageable);

    @Query("SELECT req FROM RideRequest req WHERE req.ride.carRideDetails.driver.userId = ?1")
    Page<RideRequest> findByRequestToUserId(Long id, Pageable pageable);

    @Query("SELECT req FROM RideRequest req WHERE req.requestByUser.userId = ?1 and req.ride.startTime < ?2")
    Page<RideRequest> getHistoryRidesForUser(Long id, Date now,Pageable pageable);
}
