package com.example.fitnesstrackerapp.controller;

import com.example.fitnesstrackerapp.dto.UserDTO;
import com.example.fitnesstrackerapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    private ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    private ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    private void createUser(@Valid @RequestBody UserDTO userDTO) { // return response entity
        userService.saveUser(userDTO);
    }

    @PutMapping("/update")
    private void updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete/{userId}")
    private void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
