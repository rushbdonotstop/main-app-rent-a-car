package com.example.request.repository;

import com.example.request.model.Request;
import com.example.request.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request,Long> {
    Optional<Request> findById(Long id);
    List<Request> findAll();
    List<Request> findByStatus(Status status);
    List<Request> findByUserId(Long id);

//    @Query("select r from Request r where r.startDate  <= ?2 and ?1 <= r.endDate")
//    List<Request> cancelRequests(LocalDate startdate, LocalDate enddate);

    @Query("select r.id from Request_Vehicle rv LEFT JOIN rv.request r " +
            "where r.startDate  <= ?2 and ?1 <= r.endDate " +
            "and rv.vehicleId=?3")
    List<Long> overlapingRequests(LocalDate startdate, LocalDate enddate, Long vehicleId);

    @Query("select rv.request from Request_Vehicle rv where rv.request.id in ?1")
    List<Request> bundleMembers(List<Long> reqIds);
}
