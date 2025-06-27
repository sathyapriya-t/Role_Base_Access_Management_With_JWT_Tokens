package com.spring.security.controller;

import com.spring.security.domain.RequestHeader;
import com.spring.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService service;

    @GetMapping("/authenticate")
    public String authenticateAndGenerateToken(@RequestBody RequestHeader requestHeader){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestHeader.getUsername(), requestHeader.getPassword()));
        if(authentication.isAuthenticated())
            return service.generateToken(requestHeader.getUsername(),null);
        throw new UsernameNotFoundException("Username not found");
    }
}
