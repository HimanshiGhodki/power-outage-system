package com.cde.power_supply_incident_registration;

import com.cde.power_supply_incident_registration.client.IncidentClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableHystrix
public class PowerSupplyIncidentRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerSupplyIncidentRegistrationApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public IncidentClient incidentClient(){
		return new IncidentClient();
	}

}
