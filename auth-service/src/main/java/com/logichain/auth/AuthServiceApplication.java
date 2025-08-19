package com.logichain.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@Import({ com.logichain.security.config.SecurityConfig.class })
@SpringBootApplication(scanBasePackages = {
		"com.logichain.security",
		"com.logichain.common",
		"com.logichain.auth",
		"com.logichain.inventoryservice",
		"com.logichain.order_purchase",
		"com.logichain.suppliers",
		"com.logichain.logistics"
})
public class AuthServiceApplication {	
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
