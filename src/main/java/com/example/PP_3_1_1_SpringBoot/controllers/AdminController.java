package com.example.PP_3_1_1_SpringBoot.controllers;

import com.example.PP_3_1_1_SpringBoot.models.Role;
import com.example.PP_3_1_1_SpringBoot.models.User;
import com.example.PP_3_1_1_SpringBoot.service.RoleService;
import com.example.PP_3_1_1_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping()
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private UserService userService;
    private RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String users(Principal principal, Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "adminPage";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/edit")
    public String edit(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "userInAdmin";
    }

    @GetMapping("/add")
    public String add(Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "add";
    }

    @PostMapping("/{id}/edit")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                           @RequestParam(required = false) String roleAdmin,
                           @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "userInAdmin";
        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null && roleAdmin.equals("ADMIN"))
            roles.add(roleService.findByName("ADMIN"));
        else roles.add(roleService.findByName("USER"));
        user.setRoles(roles);
        userService.update(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                          @RequestParam(required = false) String roleAdmin) {
        if (bindingResult.hasErrors())
            return "add";
        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null && roleAdmin.equals("ADMIN")) roles.add(roleService.findByName("ADMIN"));
        else roles.add(roleService.findByName("USER"));

        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showUserInfo(Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "userInAdmin";
    }
}
