package com.jwtdemo.controller;

import com.jwtdemo.model.JwtRequest;
import com.jwtdemo.model.JwtResponse;
import com.jwtdemo.service.UserService;
import com.jwtdemo.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String show(){
        return "Welcome to JWT Demo";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws  Exception{
        System.out.println("Heyyyy");
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           jwtRequest.getUsername(),
                           jwtRequest.getPassword()
                   )

           );
       }
       catch (BadCredentialsException e) {
           throw new Exception("INVALID CREDENTIALS");
       }
       final UserDetails userDetails
               =  userService.loadUserByUsername(jwtRequest.getUsername());
       final String token =
               jwtUtility.generateToken(userDetails);
    System.out.println(token);
       return new JwtResponse(token);
    }
}
