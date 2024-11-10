package com.example.pspbackend;

import com.example.pspbackend.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class PspBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PspBackendApplication.class, args);
    }

}
