package ar.edu.unnoba.ssaaii.SistVentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class SistVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistVentasApplication.class, args);
	}

}
