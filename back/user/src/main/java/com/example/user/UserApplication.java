package com.example.user;

import com.example.user.rabbit.EmailBinding;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableBinding(EmailBinding.class)
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Primary
	@Qualifier("withoutEureka")
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}


