package scoste.restau.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "scoste.restau" })
public class RestauWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauWebApplication.class, args);
	}
}
