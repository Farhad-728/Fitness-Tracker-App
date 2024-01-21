package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.entity.Workout;
import com.example.fitnesstrackerapp.enums.ExerciseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    //return type list of workouts - parameter - userId
//    @Query("select w from Workout w where w.user.id = :userId")
    List<Workout> findByUserId(Long userId);

}
