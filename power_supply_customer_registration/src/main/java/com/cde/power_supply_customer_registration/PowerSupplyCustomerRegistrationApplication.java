package com.cde.power_supply_customer_registration;

import com.cde.power_supply_customer_registration.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
@EnableEurekaClient
@EnableSwagger2
public class PowerSupplyCustomerRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerSupplyCustomerRegistrationApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.cde.power_supply_customer_registration")).build();
	}

}
