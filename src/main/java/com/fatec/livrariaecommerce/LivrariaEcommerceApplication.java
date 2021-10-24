package com.fatec.livrariaecommerce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin("http://localhost:4200")
@CrossOrigin()
@SpringBootApplication
public class LivrariaEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaEcommerceApplication.class, args);
	}

	@Bean
	public Logger logger(){
		return LogManager.getLogger(LivrariaEcommerceApplication.class);
	}
}
