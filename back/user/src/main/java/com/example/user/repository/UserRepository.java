package com.example.user.repository;

import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
    List<User> findAll();
    User findByUsernameAndPassword(String username, String password);
    User findOneById(Long id);
    User findOneByUsername(String username);
    User findOneByAgentAppId(Long id);
  
}
