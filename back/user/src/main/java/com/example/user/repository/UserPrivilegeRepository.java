package com.example.user.repository;

import com.example.user.model.User;
import com.example.user.model.UserPrivilege;
import com.example.user.model.enums.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPrivilegeRepository extends JpaRepository<UserPrivilege, Long> {
    List<UserPrivilege> findByUser(User u)

    UserPrivilege findByUserAndPrivilege(User u, Privilege addDiscount);
}
