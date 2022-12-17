package com.example.PP_3_1_1_SpringBoot.service;

import com.example.PP_3_1_1_SpringBoot.dao.RoleDao;
import com.example.PP_3_1_1_SpringBoot.models.Role;
import com.example.PP_3_1_1_SpringBoot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{
    //private final RoleRepository roleRepository;
    private final RoleDao roleDao;

    //@Autowired
    //public RoleServiceImpl(RoleRepository roleRepository) {
    //    this.roleRepository = roleRepository;
    //}

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> findAll() {
        return roleDao.findAll().stream().collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Role findByName(String role) {
        return roleDao.getRoleByName(role);
    }

}
