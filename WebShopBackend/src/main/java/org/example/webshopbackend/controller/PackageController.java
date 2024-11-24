package org.example.webshopbackend.controller;

import org.example.webshopbackend.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    public ResponseEntity buyPackage()
    {
        //TO DO
        //IMPLEMENT TO COMMUNICATE WITH PSP AND TO SEND ALL NECESSARY DATA
        return new ResponseEntity(HttpStatus.OK);
    }
}
