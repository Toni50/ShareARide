package com.alektoni.sharearide.service.impl;


import com.alektoni.sharearide.model.RouteLocation;
import com.alektoni.sharearide.model.User;
import com.alektoni.sharearide.repository.RouteLocationRepository;
import com.alektoni.sharearide.repository.UserRepository;
import com.alektoni.sharearide.service.RouteLocationService;
import com.alektoni.sharearide.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class RouteLocationServiceImpl implements RouteLocationService {

    @Autowired
    RouteLocationRepository routeLocationRepository;


    @Override
    public RouteLocation save(RouteLocation routeLocation) {
        return routeLocationRepository.save(routeLocation);
    }

    @Override
    public Optional<RouteLocation> findById(Long id) {
        return routeLocationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        routeLocationRepository.deleteById(id);
    }

    @Override
    public ArrayList<RouteLocation> getAll() {
        Iterator<RouteLocation> it = routeLocationRepository.findAll().iterator();
        ArrayList<RouteLocation> tmp = new ArrayList<>();
        while (it.hasNext()){
            tmp.add(it.next());
        }
        return tmp;
    }
}
