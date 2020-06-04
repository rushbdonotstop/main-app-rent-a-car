package com.example.catalogue.repository;

import com.example.catalogue.model.VehicleMake;
import com.example.catalogue.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
    VehicleModel findOneById(long parseLong);

    List<VehicleModel> findAll();

    List<VehicleModel> findByVehicleMake(VehicleMake vehicleMake);
}
