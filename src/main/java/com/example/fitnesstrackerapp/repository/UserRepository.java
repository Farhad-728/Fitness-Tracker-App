package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT DISTINCT u.* FROM fitness_users u \n" +
            "LEFT  JOIN  workout w \n" +
            "ON  u.id = w.user_id AND w.start_time \n" +
            "BETWEEN NOW() - INTERVAL '7 days' AND NOW() \n" +
            "WHERE w.id IS NULL", nativeQuery = true)
    List<User> findUsersWithoutWorkoutsForLast7Days();

    Optional<User> findByUsername(String username);
}
