package com.techlab.demo.controller;

import com.techlab.demo.dto.CreateUserRequest;
import com.techlab.demo.model.Role;
import com.techlab.demo.model.User;
import com.techlab.demo.repository.RoleRepository;
import com.techlab.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest req) {
        Role role = roleRepo.findByName(req.getRole())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + req.getRole()));

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setAge(req.getAge());
        user.setCapacity(String.valueOf(req.getCapacity())); // ✅ conversión a String
        user.setRoles(Set.of(role));

        return userRepo.save(user);
    }
}