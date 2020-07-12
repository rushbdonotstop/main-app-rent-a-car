package com.example.request.repository;

import com.example.request.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAll();

    Optional<Report> findById(Long id);

    List<Report> findByVehicleIdAndStartDateAndEndDate(Long vehicleId, LocalDateTime startDate, LocalDateTime endDate);

    List<Report> findAllByVehicleId(Long vehicleId);
}
