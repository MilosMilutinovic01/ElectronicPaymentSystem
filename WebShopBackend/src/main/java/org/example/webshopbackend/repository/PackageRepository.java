package org.example.webshopbackend.repository;

import org.example.webshopbackend.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
}
