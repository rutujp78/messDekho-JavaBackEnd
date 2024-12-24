package com.rutujp78.messDekho_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MessDekhoBackendApplication {

	public static void main(String[] args	) {
		SpringApplication.run(MessDekhoBackendApplication.class, args);
	}

}
