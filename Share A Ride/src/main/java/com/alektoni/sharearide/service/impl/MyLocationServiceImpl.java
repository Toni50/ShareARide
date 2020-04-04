package com.alektoni.sharearide.service.impl;


import com.alektoni.sharearide.model.MyLocation;
import com.alektoni.sharearide.repository.MyLocationRepository;
import com.alektoni.sharearide.service.MyLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class MyLocationServiceImpl implements MyLocationService {



    @Autowired
    MyLocationRepository myLocationRepository;


    @Override
    public MyLocation save(MyLocation myLocation) {
        return myLocationRepository.save(myLocation);
    }

    @Override
    public Optional<MyLocation> findById(Long rideId) {
        return myLocationRepository.findById(rideId);
    }

    @Override
    public void deleteById(Long rideId) {
        myLocationRepository.deleteById(rideId);
    }

    @Override
    public ArrayList<MyLocation> getAll() {
        Iterator<MyLocation> it = myLocationRepository.findAll().iterator();
        ArrayList<MyLocation> tmp = new ArrayList<>();
        while (it.hasNext()){
            tmp.add(it.next());
        }
        return tmp;


    }



}
