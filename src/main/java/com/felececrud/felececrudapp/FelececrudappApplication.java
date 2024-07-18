package com.felececrud.felececrudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class FelececrudappApplication {
	public static void main(String[] args) {
		SpringApplication.run(FelececrudappApplication.class, args);
	}

}

