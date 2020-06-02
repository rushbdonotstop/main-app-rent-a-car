package com.example.pricelist.repository;

import com.example.pricelist.model.VehicleDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDiscountRepository extends JpaRepository<VehicleDiscount, Long> {
}
