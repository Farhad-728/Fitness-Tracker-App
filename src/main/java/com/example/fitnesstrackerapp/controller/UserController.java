package com.example.fitnesstrackerapp.controller;
import com.example.fitnesstrackerapp.dto.UserDTO;
import com.example.fitnesstrackerapp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Tag(name = "users")
public class UserController {

    private final UserService userService;

    @GetMapping
    private ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public void createUser(@Valid @RequestBody UserDTO userDTO) { // return response entity
        userService.saveUser(userDTO);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> uploadFile(@PathVariable Long userId,
                                             @RequestParam("file") MultipartFile file) {
        userService.uploadFile(userId, file);
        return ResponseEntity.ok("File uploaded successfully. File Name: ");
    }
    @GetMapping("/getFile/{userId}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long userId) {
        byte[] image = userService.getFile(userId);

        if (image != null) {
            return ResponseEntity.ok().body(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteFile/{userId}")
    public ResponseEntity<String>removeFile(@PathVariable Long userId) {
        try {
            userService.removeFile(userId);
            return new ResponseEntity<>("File removed", HttpStatus.OK);
        } catch (RuntimeException exp) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
