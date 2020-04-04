package com.alektoni.sharearide.service.impl;

import com.alektoni.sharearide.model. RideRoute;
import com.alektoni.sharearide.repository.RideRouteRepository;
import com.alektoni.sharearide.service.RideRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class RideRouteServiceImpl implements RideRouteService {

    @Autowired
    RideRouteRepository rideRouteRepository;


    @Override
    public RideRoute save( RideRoute rideRoute) {
        return rideRouteRepository.save(rideRoute);
    }

    @Override
    public Optional<  RideRoute> findById(Long rideId) {
        return rideRouteRepository.findById(rideId);
    }

    @Override
    public void deleteById(Long rideId) {
        rideRouteRepository.deleteById(rideId);
    }

    @Override
    public ArrayList<  RideRoute> getAll() {
        Iterator<RideRoute> it = rideRouteRepository.findAll().iterator();
        ArrayList<RideRoute> tmp = new ArrayList<>();
        while (it.hasNext()){
            tmp.add(it.next());
        }
        return tmp;
    }
}
