package com.example.user.repository;

import com.example.user.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PenaltyRepository extends JpaRepository<Penalty,Long> {

    public Optional<Penalty> findById(Long id);

    public List<Penalty> findAll();

}
