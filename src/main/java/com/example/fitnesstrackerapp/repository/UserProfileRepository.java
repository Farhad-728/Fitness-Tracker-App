package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    //hibernate query language - HQL

    @Query(value = "select up from UserProfile up where  up.user.username = :username")
    UserProfile getUserProfileByUsername(@Param("username") String username);

    @Query(value = "select up from UserProfile up")
    List<UserProfile> getAll();
    @Query(value = "select u from UserProfile u where  u.id= :id")
    List<UserProfile> getUserById(@Param("id") Long id);

//    hibernate native
//    @Query(value = "select * from users limit 10", nativeQuery = true)
//    List<UserProfile> getTop10Users();
//
//    //spring data
//    UserProfile getByNameOOrderByGender(String name);
}
