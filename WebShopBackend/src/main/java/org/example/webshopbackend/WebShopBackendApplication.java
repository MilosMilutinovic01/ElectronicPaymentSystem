package org.example.webshopbackend;

import org.example.webshopbackend.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class WebShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebShopBackendApplication.class, args);
	}

}
