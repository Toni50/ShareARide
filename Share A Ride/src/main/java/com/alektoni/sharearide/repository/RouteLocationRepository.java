package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.RouteLocation;
import com.alektoni.sharearide.model.User;
import org.springframework.data.repository.CrudRepository;


public interface RouteLocationRepository extends CrudRepository<RouteLocation,Long> {
}