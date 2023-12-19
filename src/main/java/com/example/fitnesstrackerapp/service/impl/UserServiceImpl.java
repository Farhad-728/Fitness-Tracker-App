package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers() {
        return Collections.singletonList(new User());
    }
}
