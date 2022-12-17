package com.example.PP_3_1_1_SpringBoot.service;

import com.example.PP_3_1_1_SpringBoot.models.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> findAll();
    void save(Role role);
    Role findById(Long id);
    void delete(Long id);
    Role findByName(String role);
}
