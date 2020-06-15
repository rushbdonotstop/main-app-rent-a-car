package com.example.review.repository;

import com.example.review.model.Review;
import com.example.review.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByVehicleId(Long vehicleId);

    List<Review> findAllByStatus(Status pending);

    List<Review> findAllByVehicleIdAndStatus(Long vehicleId, Status approved);

    List<Review> findAllByVehicleIdAndUserIdAndStatusNot(Long vehicleId, Long userId, Status rejected);
}
