package com.example.request.repository;

import com.example.request.model.Bundle;
import com.example.request.model.Request;
import com.example.request.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request,Long> {
    Optional<Request> findById(Long id);
    List<Request> findAll();
    List<Request> findByStatus(Status status);
    List<Request> findByUserId(Long id);

    @Query("select r from Request r " +
            "where (r.startDate  <= ?2 and ?1 <= r.endDate " +
            "and r.vehicleId=?3)")
    List<Request> overlapingRequests(LocalDateTime startdate, LocalDateTime enddate, Long vehicleId);

    @Query("select r from Request r where r.bundle=?1")
    List<Request> bundleMembers(Bundle b);

    List<Request> findAllByVehicleIdAndUserIdAndStatus(Long vehicleId, Long userId, Status paid);

    @Query("select r from Request r where r.endDate <= ?1 and r.status=3 and r.bundle is null")
    List<Request> rentingFinishedRequests(LocalDateTime now);

    @Query("select r from Request r where r.endDate <= ?1 and r.status=3")
    List<Request> rentingFinishedRequestsInBundle(LocalDateTime now);

}
