package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserDTO userDTO) {
        String token = userService.login(userDTO.getUsername(), userDTO.getPassword());
        return Map.of("token", token);
    }

    @PostMapping("/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication)
    {
        return userService.getCurrentUser();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
