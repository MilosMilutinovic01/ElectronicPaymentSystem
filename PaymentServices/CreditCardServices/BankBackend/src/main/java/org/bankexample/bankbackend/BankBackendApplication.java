package org.bankexample.bankbackend;

import org.bankexample.bankbackend.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class BankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankBackendApplication.class, args);
	}

}
