package com.alektoni.sharearide.service;

import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.RouteLocation;

import java.util.ArrayList;
import java.util.Optional;

public interface RouteLocationService {
    RouteLocation save(RouteLocation routeLocation);

    Optional<RouteLocation> findById(Long id);

    void deleteById(Long id);

    ArrayList<RouteLocation> getAll();


}
