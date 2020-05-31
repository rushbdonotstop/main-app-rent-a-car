package com.example.catalogue.repository;

import com.example.catalogue.model.VehicleTransmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTransmissionRepository extends JpaRepository<VehicleTransmission, Long> {
    VehicleTransmission findOneById(long parseLong);
}
