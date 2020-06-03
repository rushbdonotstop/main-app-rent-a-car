package com.example.request.repository;

import com.example.request.model.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BundleRepository extends JpaRepository<Bundle,Long> {
    public Optional<Bundle> findById(Long id);
    public List<Bundle> findAll();

}
