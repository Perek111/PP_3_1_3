package com.example.PP_3_1_1_SpringBoot.repository;

import com.example.PP_3_1_1_SpringBoot.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role getRoleByName(String name);
}
