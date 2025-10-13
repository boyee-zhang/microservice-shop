package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.model.User;

import java.util.List;

public interface UserService {
    User register(UserDTO userDTO);
    String login(String username, String password);

    User getUserById(Long id);

    User getCurrentUser();

    User updateUser(Long id, UserDTO userDTO);

    List<User> getAllUsers();
    void deleteUser(Long id);
}
