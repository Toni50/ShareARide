package com.alektoni.sharearide.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> contacts;

    public Contact() { }

    public Contact(Long userId, List<User> contacts) {
        this.userId = userId;
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }
}
