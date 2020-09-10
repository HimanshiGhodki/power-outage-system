package com.cde.power_supply_customer_registration.controller;

import com.cde.power_supply_customer_registration.entity.Customer;
import com.cde.power_supply_customer_registration.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Customer> saveCustomerDetails(@Validated @RequestBody Customer customer){
        Customer newCustomer = customerService.saveCustomerDetails(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.FOUND).body(customers);
    }

    @HystrixCommand(fallbackMethod = "getCustomerByUsernameFallback")
    @GetMapping("/{username}")
    public ResponseEntity<Customer> getCustomerByUsername(@PathVariable("username") String username){
        Customer customer = customerService.getCustomerByUsername(username);
        return ResponseEntity.status(HttpStatus.FOUND).body(customer);
    }

    public ResponseEntity<Customer> getCustomerByUsernameFallback(@PathVariable("username") String username){
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/admin/{username}")
    public ResponseEntity<Customer> updateCustomerDetails(@PathVariable(value = "username") String username,
                                                          @Validated @RequestBody Customer customer){
        Customer updatedCustomer = customerService.updateCustomerDetails(username, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{registration_no}")
    public void deleteCustomer(@PathVariable(value = "registration_no") Integer registration_no){
        customerService.deleteCustomer(registration_no);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArguments(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String,String> handleNoSuchElementException(NoSuchElementException e){
        Map<String,String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return errors;
    }
}
