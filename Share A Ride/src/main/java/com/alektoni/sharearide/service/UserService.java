package com.alektoni.sharearide.service;



import com.alektoni.sharearide.model.User;

import java.util.ArrayList;
import java.util.Optional;


public interface UserService {

    User save(User user);

    Optional<User> findById(Long aLong);

    void deleteById(Long aLong);

    ArrayList<User> getAll();

    Optional<User> findByEmail(String email);



}
