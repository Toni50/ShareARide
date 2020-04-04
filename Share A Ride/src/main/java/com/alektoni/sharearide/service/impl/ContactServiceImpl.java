package com.alektoni.sharearide.service.impl;


import com.alektoni.sharearide.model.Car;
import com.alektoni.sharearide.model.Contact;
import com.alektoni.sharearide.repository.CarRepository;
import com.alektoni.sharearide.repository.ContactRepository;
import com.alektoni.sharearide.service.CarService;
import com.alektoni.sharearide.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Optional<Contact> findById(Long rideId) {
        return contactRepository.findById(rideId);
    }


    @Override
    public void deleteById(Long rideId) {
        contactRepository.deleteById(rideId);
    }

    @Override
    public ArrayList<Contact> getAll() {
        Iterator<Contact> it = contactRepository.findAll().iterator();
        ArrayList<Contact> tmp = new ArrayList<>();
        while (it.hasNext()) {
            tmp.add(it.next());
        }
        return tmp;
    }

    @Override
    public ArrayList<Contact> findByUserId(Long aLong) {
        return contactRepository.findByUserId(aLong);
    }


}
