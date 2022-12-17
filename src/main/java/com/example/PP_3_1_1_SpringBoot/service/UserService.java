package com.example.PP_3_1_1_SpringBoot.service;

import com.example.PP_3_1_1_SpringBoot.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> findAll();

    public void save(User user);
    public void update(User user);

    public void delete(Long id); //by id

    public User findById(Long id);
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
