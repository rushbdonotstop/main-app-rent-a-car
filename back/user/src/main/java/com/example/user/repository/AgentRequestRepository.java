package com.example.user.repository;

import com.example.user.model.AgentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRequestRepository extends JpaRepository<AgentRequest, Long> {
    AgentRequest findOneById(Long id);
}
