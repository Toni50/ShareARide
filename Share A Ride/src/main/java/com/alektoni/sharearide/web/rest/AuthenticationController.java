package com.alektoni.sharearide.web.rest;


import com.alektoni.sharearide.security.AuthenticationRequest;
import com.alektoni.sharearide.security.AuthenticationResponse;
import com.alektoni.sharearide.security.JwtUtil;
import com.alektoni.sharearide.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sharearide/rest/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    public AuthenticationController(){ }



    @RequestMapping(value="/authenticate", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Incorrect password", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    //todo validate toekn method




}
