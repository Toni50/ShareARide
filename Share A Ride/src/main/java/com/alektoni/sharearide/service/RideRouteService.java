package com.alektoni.sharearide.service;


import com.alektoni.sharearide.model.RideRoute;

import java.util.ArrayList;
import java.util.Optional;

public interface RideRouteService {

    RideRoute save(RideRoute rideRoute);

    Optional<RideRoute> findById(Long aLong);

    void deleteById(Long aLong);

    ArrayList<RideRoute> getAll();
}
