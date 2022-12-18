package com.example.PP_3_1_1_SpringBoot.util;

import com.example.PP_3_1_1_SpringBoot.models.Role;
import com.example.PP_3_1_1_SpringBoot.models.User;
import com.example.PP_3_1_1_SpringBoot.service.RoleService;
import com.example.PP_3_1_1_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitializationUserDB {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitializationUserDB(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsersWithRoles() {

        Role role1 = new Role("ADMIN");
        Role role2 = new Role( "USER");

        roleService.save(role1);
        roleService.save(role2);

        Set<Role> set = new HashSet<>();
        set.add(role1);
        set.add(role2);

        User user1 = new User(new BCryptPasswordEncoder(8).encode("123qweasd"), "Урусов", "Артур", 22,  "urusovartur@gmail.com", set );

        userService.save(user1);

    }
}
