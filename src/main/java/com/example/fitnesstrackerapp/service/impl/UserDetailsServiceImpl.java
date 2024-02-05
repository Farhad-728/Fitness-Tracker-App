package com.example.fitnesstrackerapp.service.impl;

import com.example.fitnesstrackerapp.entity.Role;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.exception.UserNotFoundException;
import com.example.fitnesstrackerapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found for {}" + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getRole())

        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
       Collection<GrantedAuthority> collection = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.getName()));
       log.info(String.valueOf(collection));
        return collection;
    }
}
