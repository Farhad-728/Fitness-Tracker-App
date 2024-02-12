package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.dto.UserDTO;
import com.example.fitnesstrackerapp.entity.Role;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.entity.UserProfile;
import com.example.fitnesstrackerapp.exception.RoleNotFoundException;
import com.example.fitnesstrackerapp.exception.UserNotFoundException;
import com.example.fitnesstrackerapp.exception.UserProfileNotFoundException;
import com.example.fitnesstrackerapp.mapper.UserMapper;
import com.example.fitnesstrackerapp.repository.RoleRepository;
import com.example.fitnesstrackerapp.repository.UserProfileRepository;
import com.example.fitnesstrackerapp.repository.UserRepository;
import com.example.fitnesstrackerapp.service.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserMapper userMapper;
    //    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDTO> getUsers() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        return userMapper.fromProfilesToDTOList(userProfiles);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUsername(username);
        return profile.map(p -> userMapper.fromProfileToDTO(p))
                .orElseThrow(() -> {
                    throw new UserProfileNotFoundException("Profile not found");
                }); //replace with custom exception
    }
    //    public UserDTO getUserByUsername(String username) {
//        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUsername(username);
//        return profile.map(p -> {
//            return userMapper.fromProfileToDTO(p);
//        }).orElseThrow(() -> new RuntimeException("Profile not found"));
//    }


    @Override
    public void saveUser(UserDTO userDTO) {
        //check if username is already in db
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        UserProfile profile = userMapper.fromDTOToProfile(userDTO);
        // findByRoleName - Role obj
        Role role = roleRepository.findByName(userDTO.getRoleName()).orElse(null);
        //set Role obj to user
        profile.getUser().setRole(role);
        //encode password
//        profile.getUser().setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userProfileRepository.save(profile);
    }


    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUsername(userDTO.getUsername());
        if (profile.isPresent()) {
            UserProfile updatedProfile = userMapper.fromDTOToProfileForUpdate(userDTO, profile.get().getUser().getId());
            if (StringUtils.isNotBlank(userDTO.getRoleName())) {
                Role role = roleRepository.findByName(userDTO.getRoleName()).orElseThrow(() -> {
                    throw new RoleNotFoundException("Role not found");
                }); //throw exception
                updatedProfile.getUser().setRole(role);
            }
            userProfileRepository.save(updatedProfile);
        } else {
            throw new UserProfileNotFoundException("Profile not found");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUserId(userId);
        profile.ifPresentOrElse(
                userProfileRepository::delete,
                () -> {
                    throw new UserProfileNotFoundException("Profile not found");
                }
        );
    }

    @Override
    public void uploadFile(Long userId, MultipartFile file) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUserId(userId);
        if (profile.isPresent()) {
            try {
                long size = file.getSize();
                if (file.getBytes().length > (1024 * 1024)) {
                    throw new Exception("File size exceeds maximum limit");
                }
                profile.get().setImage(file.getBytes());
                userProfileRepository.save(profile.get());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public byte[] getFile(Long userId) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUserId(userId);
        if (profile.isPresent()) {
            return profile.get().getImage();
        }
        return null;
    }

    @Override
    public void removeFile(Long userId) {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUserId(userId);
        profile.ifPresentOrElse(
                p -> {
                    p.setImage(null);
                    userProfileRepository.save(p);
                },
                () -> {
                    throw new UserProfileNotFoundException("Profile not found");
                }
        );
    }

    @Override
    public List<User> findUsersWithoutWorkoutsForTheWeek() {
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        List<User> usersWithoutWorkouts = new ArrayList<>();

        return usersWithoutWorkouts;

    }

//    public final static String uploadDir = "C:\\Users\\Shams\\Zoom";



/*    public void uploadFilePC(Long userId, MultipartFile file) {
        // Validate and save the file content to UserProfile entity
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUserId(userId);
        String fileName = profile.get().getId() + "_" + file.getOriginalFilename();
        fileName.setProfileImageName(fileName);

        // Save the file to the file server directory
        Path filePath = Path.of(uploadDir).resolve(fileName);
        try {
//            Files.write(filePath, file.getBytes());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            userProfileRepository.save(p);
        } catch (IOException e) {
            // Handle exception (e.g., log error)
            e.printStackTrace();
        }
    }

    public byte[] getFilePC(Long userId) throws IOException {
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUserId(userId);
        String fileName = userProfile.getProfileImageName();

        // Retrieve file content from the file server directory
        Path filePath = Path.of(uploadDir).resolve(fileName);
        return Files.readAllBytes(filePath);
    }

    public void removeFilePC(Long userId) {
        // Remove file from the file server directory
        Optional<UserProfile> profile = userProfileRepository.getUserProfileByUserId(userId);
        String fileName = userProfile.getProfileImageName();
        Path filePath = Path.of(uploadDir).resolve(fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            // Handle exception (e.g., log error)
            e.printStackTrace();
        }

        // Update UserProfile entity to remove the file name
        userProfile.setProfileImage(null);
        saveUserProfile(userProfile);
    }*/

}
