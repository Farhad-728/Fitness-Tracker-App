package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.UserDTO;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.entity.UserProfile;
import com.example.fitnesstrackerapp.entity.Workout;
import com.example.fitnesstrackerapp.mapper.UserMapper;
import com.example.fitnesstrackerapp.repository.UserProfileRepository;
import com.example.fitnesstrackerapp.repository.UserRepository;
import com.example.fitnesstrackerapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getUsers() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        return userMapper.fromProfilesToDTOList(userProfiles);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUsername(username);
        return profile.map(p -> userMapper.fromProfileToDTO(p))
                .orElseThrow(() -> new RuntimeException("profile not found")); //replace with custom exception
    }


    @Override
    public void saveUser(UserDTO userDTO) {
        UserProfile profile = userMapper.fromDTOToProfile(userDTO);
        userProfileRepository.save(profile);
    }


    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUsername(userDTO.getUsername());
        profile.ifPresentOrElse(
                p -> {
                    UserProfile updatedProfile = userMapper.fromDTOToProfileForUpdate(userDTO, p.getUser().getId());
                    userProfileRepository.save(updatedProfile);
                },
                () -> new RuntimeException("Profile not found")
        );
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUserId(userId);
        profile.ifPresentOrElse(
                userProfileRepository::delete,
                () -> new RuntimeException("Profile not found")
        );
    }
}
