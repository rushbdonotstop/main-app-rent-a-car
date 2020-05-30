package com.example.pricelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PricelistApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricelistApplication.class, args);
	}

}
