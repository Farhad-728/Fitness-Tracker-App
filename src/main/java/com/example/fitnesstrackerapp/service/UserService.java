package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.dto.UserDTO;
import java.util.List;

import com.example.fitnesstrackerapp.entity.User;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {
    List<UserDTO> getUsers();

    void saveUser(UserDTO userDTO);

    UserDTO getUserByUsername(String username);

    void updateUser(UserDTO userDTO);

    void deleteUser(Long userId);

    void uploadFile(Long userId, MultipartFile file);

    byte[] getFile(Long userId);

    void removeFile(Long userId);

    List<User> findUsersWithoutWorkoutsForTheWeek();
}
