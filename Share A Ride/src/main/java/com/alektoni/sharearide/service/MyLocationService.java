package com.alektoni.sharearide.service;

import com.alektoni.sharearide.model.MyLocation;

import java.util.Optional;
import java.util.ArrayList;

public interface MyLocationService {

    MyLocation save(MyLocation myLocation);

    Optional<MyLocation> findById(Long aLong);

    void deleteById(Long aLong);

    ArrayList<MyLocation> getAll();
}
