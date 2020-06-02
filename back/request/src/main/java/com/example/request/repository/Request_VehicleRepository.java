package com.example.request.repository;


import com.example.request.model.Request;
import com.example.request.model.Request_Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Request_VehicleRepository extends JpaRepository<Request_Vehicle, Long> {
    Optional<Request_Vehicle> findById(Long id);
    List<Request_Vehicle> findAll();

    @Query("select rv.request from Request_Vehicle rv where rv.request.id in ?1")
    List<Request> bundleMembers(List<Long> reqIds);
}
