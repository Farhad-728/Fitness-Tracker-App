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
import static java.util.Objects.isNull;

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
        UserProfile profile = userProfileRepository.getUserProfileByUsername(username);

        return buildUserDto(profile);
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
    public void createUser(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .build();
        User newUser = userRepository.save(user);

        UserProfile userProfile = UserProfile.builder()
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .weight(userDTO.getWeight())
                .gender(userDTO.getGender())
                .user(newUser)
                .build();
        userProfileRepository.save(userProfile);
    }
    @Override
    public void updateUser(UserDTO userDTO) {
        UserProfile profile =
                userProfileRepository.getUserProfileByUsername(userDTO.getUsername());
        //update
        if (!isNull(profile)) {
            buildUserProfile(userDTO, profile);
        } else {
            User updateUser = User
                    .builder()
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .build();
            userRepository.save(updateUser);
        }
    }
    private UserDTO buildUserProfile(UserDTO userDTO, UserProfile profile) {
        User updateUser = User.builder()
                .id(profile.getUser().getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        userRepository.save(updateUser);

        UserProfile userProfile = UserProfile.builder()
                .id(profile.getId())
                .name(userDTO.getName())
                .gender(userDTO.getGender())
                .age(userDTO.getAge())
                .weight(userDTO.getWeight())
                .build();
        userProfileRepository.save(userProfile);
    }
}
