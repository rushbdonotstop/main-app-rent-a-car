package com.example.catalogue.repository;

import com.example.catalogue.model.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Long> {
    VehicleMake findOneById(long parseLong);

}
