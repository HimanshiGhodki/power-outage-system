package com.example.zuulgateway.service;

import com.example.zuulgateway.entity.Customers;
import com.example.zuulgateway.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customers> customer = customerRepository.findByUsername(username);
        customer.orElseThrow(() -> new UsernameNotFoundException("No User Found"));
        return customer.map(MyUserDetails::new).get();
    }

}

