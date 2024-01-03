package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.Goal;
import com.example.fitnesstrackerapp.entity.User;
import com.example.fitnesstrackerapp.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    @Query(value = "select up from Goal up")
    List<Goal> getAll();

    @Transactional
    @Modifying
    @Query(value = "delete from Goal g where  g.id = :id")
    void deleteGoalById(@Param("id") Long id);

//    @Modifying
//    void deleteByUserId(Long id);

}
