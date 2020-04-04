package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;


public interface ContactRepository extends CrudRepository<Contact,Long> {

    ArrayList<Contact> findByUserId(Long aLong);
}
