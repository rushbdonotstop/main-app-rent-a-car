package com.example.user.repository;

import com.example.user.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken save(VerificationToken verificationToken);



    @Query("select vT from VerificationToken vT where vT.token = ?1")
    VerificationToken findToken(String token);
}
