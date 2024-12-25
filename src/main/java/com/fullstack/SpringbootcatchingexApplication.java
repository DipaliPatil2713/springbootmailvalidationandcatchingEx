package com.fullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootcatchingexApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootcatchingexApplication.class, args);
	}

}
