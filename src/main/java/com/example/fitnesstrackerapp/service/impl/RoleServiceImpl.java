package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.entity.Role;
import com.example.fitnesstrackerapp.repository.RoleRepository;
import com.example.fitnesstrackerapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

private final RoleRepository roleRepository;
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
