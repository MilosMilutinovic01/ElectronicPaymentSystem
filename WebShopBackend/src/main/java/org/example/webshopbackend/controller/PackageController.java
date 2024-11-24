package org.example.webshopbackend.controller;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.example.webshopbackend.dto.BuyPackageRequestDTO;
import org.example.webshopbackend.dto.PSPRequestDTO;
import org.example.webshopbackend.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(value = "/api/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @PreAuthorize("hasPermission('CUSTOMER')")
    @GetMapping("/get-all")
    public ResponseEntity getPackages()
    {
        return packageService.getAllPackages();
    }

    @PreAuthorize("hasPermission('CUSTOMER')")
    @PostMapping("/buy")
    public ResponseEntity buyPackage(@RequestBody BuyPackageRequestDTO request)
    {
        //FOR THE PURPOSE IF API KEY IS NEEDED
//        Dotenv dotenv = Dotenv.configure()
//            .directory("./") // Specify the directory containing the .env file
//            .load();;
//        String apiKey = dotenv.get("MERCHANT_API_KEY");
        PSPRequestDTO dto = new PSPRequestDTO(new BigDecimal(packageService.getPackageById(request.getPackageId()).getPrice()), UUID.randomUUID().toString(), LocalDateTime.now().toInstant(ZoneOffset.UTC).toString());
        //TO DO Change uri
        WebClient.create()
                .post()
                .uri("http://localhost:8081/api/merchant")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .subscribe(response -> log.info("Service responded: " + response),
                        error -> log.error("Error occurred while creating merchant", error));
        return new ResponseEntity(HttpStatus.OK);
    }
}
