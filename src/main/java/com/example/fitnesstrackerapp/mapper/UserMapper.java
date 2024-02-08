package com.example.fitnesstrackerapp.mapper;

import com.example.fitnesstrackerapp.dto.UserDTO;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.entity.UserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserMapper {
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public User mapJsonObjectToJson(String jsonString) {
        User user = modelMapper.map(jsonString, User.class);
        return user;
    }

    public UserDTO fromProfileToDTO(UserProfile profile) {
        UserDTO userDTO = modelMapper.map(profile, UserDTO.class);
        log.error(String.valueOf(userDTO));
        return userDTO;
    }

    public List<UserDTO> fromProfilesToDTOList(List<UserProfile> userProfiles) {
        return userProfiles.stream()
                .map(this::fromProfileToDTO)
                .collect(Collectors.toList());
    }

    public UserProfile fromDTOToProfile(UserDTO userDTO) {
        User newUser = fromDTOToUser(userDTO);
        return fromDTOToProfile(userDTO, newUser);
    }

    public UserProfile fromDTOToProfile(UserDTO userDTO, User user) {
        UserProfile userProfile = modelMapper.map(userDTO, UserProfile.class);
        userProfile.setUser(user);
        return userProfile;
    }

    public UserProfile fromDTOToProfileForUpdate(UserDTO userDTO, Long userId) {
        User user = fromDTOToUser(userDTO, userId);
        return fromDTOToProfile(userDTO, user);
    }

    public User fromDTOToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public User fromDTOToUser(UserDTO userDTO, Long userId) {
        User user = modelMapper.map(userDTO, User.class);
        user.setId(userId);
        return user;
    }
}
