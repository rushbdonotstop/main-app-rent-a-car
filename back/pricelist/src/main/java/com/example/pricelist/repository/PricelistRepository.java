package com.example.pricelist.repository;

import com.example.pricelist.model.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PricelistRepository extends JpaRepository<Pricelist, Long> {
    List<Pricelist> findByVehicleId(Long vehicleId);

    List<Pricelist> findAll();

 
}
