package com.example.PP_3_1_1_SpringBoot.dao;


import com.example.PP_3_1_1_SpringBoot.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDao {
    public List<User> findAll();

    public void save(User user);
    public void update(User user);

    public void deleteById(Long id); //by id

    public User findById(Long id);
    public User findByEmail(String email);

}
