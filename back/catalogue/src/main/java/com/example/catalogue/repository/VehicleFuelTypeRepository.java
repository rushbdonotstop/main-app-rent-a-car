package com.example.catalogue.repository;

import com.example.catalogue.model.VehicleFuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleFuelTypeRepository extends JpaRepository<VehicleFuelType, Long> {
    VehicleFuelType findOneById(long parseLong);
    VehicleFuelType findByValue(String value);
}
