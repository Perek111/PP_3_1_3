package com.example.PP_3_1_1_SpringBoot.repository;

import com.example.PP_3_1_1_SpringBoot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
