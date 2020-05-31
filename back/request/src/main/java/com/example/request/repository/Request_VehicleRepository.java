package com.example.request.repository;


import com.example.request.model.Request_Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Request_VehicleRepository extends JpaRepository<Request_Vehicle, Long> {
    Optional<Request_Vehicle> findById(Long id);
    List<Request_Vehicle> findAll();
}
