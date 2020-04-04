package com.alektoni.sharearide.service;

import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.Contact;

import java.util.ArrayList;
import java.util.Optional;

public interface ContactService {
    Contact save(Contact contact);

    Optional<Contact> findById(Long aLong);

    void deleteById(Long aLong);

    ArrayList<Contact> getAll();

    ArrayList<Contact> findByUserId(Long aLong);
}
