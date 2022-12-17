package com.example.PP_3_1_1_SpringBoot.service;

import com.example.PP_3_1_1_SpringBoot.dao.UserDao;
import com.example.PP_3_1_1_SpringBoot.models.User;
import com.example.PP_3_1_1_SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserDao userDao;

    //@Autowired
    //public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
    //    this.userRepository = userRepository;
    //    this.roleService = roleService;
    //}

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(String.format("User %s not found", username));
        return user;
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }
}