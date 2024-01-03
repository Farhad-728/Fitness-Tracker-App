package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.UserDTO;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.entity.UserProfile;
import com.example.fitnesstrackerapp.repository.UserProfileRepository;
import com.example.fitnesstrackerapp.repository.UserRepository;
import com.example.fitnesstrackerapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    @Override
    public List<UserDTO> getUsers() {
        List<UserProfile> userProfiles = userProfileRepository.getAll();
        return mapUserEntityToDto(userProfiles);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUsername(username);
        return profile.map(this::buildUserDto)
                .orElseThrow(() -> new RuntimeException("profile not found")); //replace with custom exception
    }

    private List<UserDTO> mapUserEntityToDto(List<UserProfile> userProfiles) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserProfile up : userProfiles) {
            userDTOS.add(buildUserDto(up));
        }
        return userDTOS;
    }

    private UserDTO buildUserDto(UserProfile up) {
        return UserDTO.builder()
                .username(up.getUser().getUsername())
                .weight(up.getWeight())
                .age(up.getAge())
                .email(up.getUser().getEmail())
                .gender(up.getGender())
                .name(up.getName())
                .build();
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User newUser = buildUserObject(userDTO);
        createProfile(userDTO, newUser);
    }

    private User buildUserObject(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .build();
    }

    private void createProfile(UserDTO userDTO, User user) {
        UserProfile userProfile = UserProfile.builder()
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .weight(userDTO.getWeight())
                .gender(userDTO.getGender())
                .user(user)
                .build();
        userProfileRepository.save(userProfile);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUsername(userDTO.getUsername());
        profile.ifPresentOrElse(
                p -> {
                    UserProfile updatedProfile = buildUserProfile(userDTO, p);
                    userProfileRepository.save(updatedProfile);
                },
                () -> new RuntimeException("Profile not found")
        );
    }

    //SINGLE RESPONSIBILITY
    private UserProfile buildUserProfile(UserDTO userDTO, UserProfile profile) {
        User updateUser = buildUpdatedUser(userDTO, profile.getUser().getId());

        UserProfile updatedProfile = UserProfile.builder()
                .id(profile.getId())
                .name(userDTO.getName())
                .gender(userDTO.getGender())
                .age(userDTO.getAge())
                .weight(userDTO.getWeight())
                .user(updateUser)
                .build();
        return updatedProfile;
    }

    private User buildUpdatedUser(UserDTO userDTO, Long userId) {
        return User.builder()
                .id(userId)
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

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
