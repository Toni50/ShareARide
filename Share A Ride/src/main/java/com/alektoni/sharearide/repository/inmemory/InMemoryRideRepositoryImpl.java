/*
package com.alektoni.sharearide.repository.inmemory;


import com.alektoni.sharearide.model.DataHolder;
import com.alektoni.sharearide.model.Ride;
import com.alektoni.sharearide.repository.RideRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;


@Repository
public class InMemoryRideRepositoryImpl implements RideRepository {


    @Override
    public Ride save(Ride ride) {
        this.findById(ride.getRideId()).ifPresent(DataHolder.rides::remove);
        DataHolder.rides.add(ride);
        return ride;
    }

    @Override
    public Optional<Ride> findById(Long rideId) {
        return DataHolder.rides.stream()
                .filter(ride -> ride.getRideId() == rideId)
                .findFirst();
    }

    @Override
    public void deleteById(Long rideId) {
        this.findById(rideId).ifPresent(DataHolder.rides::remove);
    }

    @Override
    public ArrayList<Ride> getAll() {
        return DataHolder.rides;
    }


}
*/
