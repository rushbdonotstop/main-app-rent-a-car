package com.example.vehicle.repository;

import com.example.vehicle.model.VehicleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleImageRepository extends JpaRepository<VehicleImage, Long> {
    Optional<VehicleImage> findByName(String name);
}
