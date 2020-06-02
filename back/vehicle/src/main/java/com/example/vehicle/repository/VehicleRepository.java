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

    List<Vehicle> findByFuelTypeId(Long Id);

    List<Vehicle> findByMakeId(Long Id);

    List<Vehicle> findByModelId(Long Id);

    List<Vehicle> findByStyleId(Long Id);

    List<Vehicle> findByTransmissionId(Long Id);

    Vehicle findByPicturePath(String s);
}
