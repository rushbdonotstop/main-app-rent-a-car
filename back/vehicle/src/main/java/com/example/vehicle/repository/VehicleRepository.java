package com.example.vehicle.repository;

import com.example.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAll();

    Vehicle findOneById(Long Id);

    Vehicle save(Vehicle vehicle);

    void removeById(Long Id);

    List<Vehicle> findByCollisionProtection(boolean collisionProtection);

    List<Vehicle> findByChildrenSeats(int childrenSeats);

    List<Vehicle> findByStartDate(Date startDate);

    List<Vehicle> findByEndDate(Date endDate);

    List<Vehicle> findByVehicleFuelTypeId(Long Id);

    List<Vehicle> findByVehicleMakeId(Long Id);

    List<Vehicle> findByVehicleModelId(Long Id);

    List<Vehicle> findByVehicleStypeId(Long Id);

    List<Vehicle> findByVehicleTransmissionId(Long Id);
}
