package com.cde.power_supply_incident_registration.client;

import com.cde.power_supply_incident_registration.service.IncidentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class IncidentClient {

    @Autowired
    private RestTemplate restTemplate;

    public  static final Logger log= LoggerFactory.getLogger(IncidentService.class);

    @HystrixCommand(fallbackMethod = "getCustomerDetailsFallback")
    public String getCustomerDetails(String username){
        log.info("Getting customer details with username: "+username);
        String response = restTemplate
                .exchange("http://localhost:8100/CUSTOMER-REGISTRATION-CLIENT/customers/{username}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        },username).getBody();

        return response.toString();
    }

    private String getCustomerDetailsFallback(String username){
        log.info("Customer Service is down! Fallback method called");

        return "Customer Service is taking too long to respond or is down. Please try again later";
    }
}
