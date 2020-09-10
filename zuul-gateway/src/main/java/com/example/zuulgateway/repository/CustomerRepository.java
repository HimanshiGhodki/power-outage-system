package com.example.zuulgateway.repository;

import com.example.zuulgateway.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    Optional<Customers> findByUsername(String username);
}
