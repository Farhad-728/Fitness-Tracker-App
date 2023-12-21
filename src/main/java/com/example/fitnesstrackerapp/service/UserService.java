package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.UserDTO;

import java.util.List;


public interface UserService {
    List<UserDTO> getUsers();

    void createUser(UserDTO userDTO);

    UserDTO getUserByUsername(String username);

    void updateUser(UserDTO userDTO);
}
