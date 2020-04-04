package com.alektoni.sharearide.repository;

import com.alektoni.sharearide.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {


    @Query("SELECT user FROM User user WHERE user.email = ?1 ")
    Optional<User> findByEmail(String email);
}
