package com.cde.power_supply_customer_registration.service;

import com.cde.power_supply_customer_registration.entity.Customer;
import com.cde.power_supply_customer_registration.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public  static final Logger log= LoggerFactory.getLogger(CustomerService.class);

    public Customer saveCustomerDetails(Customer customer) {
        log.info("saving customer");
        customerRepository.save(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        log.info("get all customer");
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer getCustomerByUsername(String username) {
        log.info("getting customer for username: "+username);
        Optional<Customer> customer = customerRepository.findByUsername(username);
        customer.orElseThrow(() -> new NoSuchElementException("No incident found for this docket no."));
        return customer.get();
    }

    public void deleteCustomer(Integer registration_no) {
        log.info("deleting customer for registration number: "+registration_no);
        Optional<Customer> optionalCustomer = customerRepository.findById(registration_no);
        optionalCustomer.orElseThrow(() -> new NoSuchElementException("No incident found for this docket no."));
        customerRepository.delete(optionalCustomer.get());
    }

    public Customer updateCustomerDetails(String username, Customer customer) {
        log.info("updating customer for username: "+username);
        Optional<Customer> customerOptional = customerRepository.findByUsername(username);
        customerOptional.orElseThrow(() -> new NoSuchElementException("No incident found for this docket no."));
        Customer updateCustomer = customerOptional.get();
        updateCustomer.setAccount(customer.getAccount());
        updateCustomer.setAddress(customer.getAddress());
        updateCustomer.setContact_no(customer.getContact_no());
        updateCustomer.setCustomer_name(customer.getCustomer_name());
        updateCustomer.setCountry(customer.getCountry());
        updateCustomer.setDate_of_birth(customer.getDate_of_birth());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setPAN_no(customer.getPAN_no());
        updateCustomer.setPassword(customer.getPassword());
        updateCustomer.setState(customer.getState());
        customerRepository.saveAndFlush(updateCustomer);
        return updateCustomer;
    }
}
