package com.example.PP_3_1_1_SpringBoot.dao;

import com.example.PP_3_1_1_SpringBoot.models.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findAll();
    void save(Role role);
    Role findById(Long id);
    void delete(Long id);
    Role getRoleByName(String name);
}
