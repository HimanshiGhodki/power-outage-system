package com.example.cloudgateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/customerFallBack")
    public Mono<String> customerServiceFallBack() {
        return Mono.just("Customer Service is taking too long to respond or is down. Please try again later");
    }
    @RequestMapping("/incidentFallback")
    public Mono<String> incidentServiceFallBack() {
        return Mono.just("Incident Service is taking too long to respond or is down. Please try again later");
    }
}
