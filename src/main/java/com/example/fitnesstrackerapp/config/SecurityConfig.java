package com.example.fitnesstrackerapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService user() {

        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user")
                .password("userPassword")
                .roles("USER")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminPassword")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                        .userDetailsService(userDetailsServiceImpl)
                        .passwordEncoder(passwordEncoder());

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        return httpSecurity.authorizeHttpRequests(
                        outh -> outh.requestMatchers("/v1/users/**").hasRole("ADMIN")
                                .requestMatchers("/v1/goals/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/v1/roles/**").hasRole("USER")
                                .requestMatchers("/v1/workouts/**").permitAll()
                                .requestMatchers("/v1/exercise/**").permitAll()
                                .requestMatchers("/v1/tracker/**").hasAnyRole("USER", "ADMIN")
                                .anyRequest()
                                .authenticated())
                .authenticationManager(authenticationManager)
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
