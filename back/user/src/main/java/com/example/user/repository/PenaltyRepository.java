package com.example.user.repository;

import com.example.user.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {

    public Optional<Penalty> findById(Long id);

    public List<Penalty> findAll();

    @Query(value = "select p from User u join u.userDetails ud join ud.penalties p where p.penaltyStatus = 0 and u.id = ?1")
    public List<Penalty> unpaidPenalties(Long userId);
}
