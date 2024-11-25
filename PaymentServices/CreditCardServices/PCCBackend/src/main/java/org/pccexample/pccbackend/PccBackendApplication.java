package org.pccexample.pccbackend;

import org.pccexample.pccbackend.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class PccBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PccBackendApplication.class, args);
	}

}
