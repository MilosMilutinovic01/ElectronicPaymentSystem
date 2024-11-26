package org.example.webshopbackend.service;

import org.example.webshopbackend.model.Package;
import org.example.webshopbackend.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
    @Autowired
    PackageRepository packageRepository;

    public ResponseEntity getAllPackages() {
        return new ResponseEntity(packageRepository.findAll(), HttpStatus.OK);
    }

    public Package getPackageById(Long id) {
        return packageRepository.findById(id).get();
    }
}
