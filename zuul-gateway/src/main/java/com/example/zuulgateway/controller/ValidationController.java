package com.example.zuulgateway.controller;

import com.example.zuulgateway.entity.AuthenticationRequest;
import com.example.zuulgateway.entity.AuthenticationResponse;
import com.example.zuulgateway.repository.CustomerRepository;
import com.example.zuulgateway.service.MyUserDetailService;
import com.example.zuulgateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailService userDetailsService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /*@RequestMapping("/customerFallBack")
    public ResponseEntity<String> CustomerServiceFallBack(){
        String message = "Customer Service is taking too long to respond or is down. Please try again later";
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
    }

    @RequestMapping("/incidentFallBack")
    public ResponseEntity<String> IncidentServiceFallBack(){
        String message = "Incident Service is taking too long to respond or is down. Please try again later";
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(message);
    }*/
}

