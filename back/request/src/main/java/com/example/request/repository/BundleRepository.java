package com.example.request.repository;

import com.example.request.model.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BundleRepository extends JpaRepository<Bundle,Long> {
    Bundle findOneById(Long id);
    List<Bundle> findAll();
    Bundle save(Bundle bundle);

}
