package com.example.fitnesstrackerapp.controller;

import com.example.fitnesstrackerapp.entity.Role;
import com.example.fitnesstrackerapp.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/roles")
@Tag(name = "roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity createRole(@RequestBody Role role) {
        roleService.saveRole(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
