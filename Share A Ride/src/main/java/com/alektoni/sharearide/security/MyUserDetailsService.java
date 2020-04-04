package com.alektoni.sharearide.security;

import com.alektoni.sharearide.exceptions.NotFoundException;
import com.alektoni.sharearide.model.User;
import com.alektoni.sharearide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByEmail(username);
        if(optUser.isPresent()){
            return new MyUserDetails(optUser.get());
        }
        throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, "User with mail "+username+" is not found");
    }
}
