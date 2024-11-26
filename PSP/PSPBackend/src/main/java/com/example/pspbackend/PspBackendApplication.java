package com.example.pspbackend;

import com.example.pspbackend.config.RsaKeyConfigProperties;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class PspBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PspBackendApplication.class, args);
    }

    @PreDestroy
    public void clearLogFile(){
        try(PrintWriter pw = new PrintWriter(new File("src/main/resources/database.log"))){
            System.out.println("Succesfully cleared content of log file!");
        } catch (FileNotFoundException e) {
            System.out.println("Error occured while clearing log file content!");
        }
    }

}
