package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.Exercise;
import com.example.fitnesstrackerapp.enums.ExerciseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Page<Exercise> findByNameContainingIgnoreCase(String name, PageRequest pageRequest);

    Page<Exercise> findByTypeOrderByDurationDesc(ExerciseType type, PageRequest pageRequest);

    Page<Exercise> findByDurationGreaterThan(double duration, PageRequest pageRequest);

    @Query("SELECT e FROM Exercise e WHERE e.duration > :minDuration and e.duration < :maxDuration")
    Page<Exercise> findByDurationBetweenMaxAndMin(@Param("minDuration") double minDuration,
                                                  @Param("maxDuration") double maxDuration,
                                                  PageRequest pageRequest);

    @Query("SELECT e FROM Exercise e WHERE e.type = :exerciseType AND e.duration > :minDuration")
    Page<Exercise> findByTypeAndDuration(@Param("exerciseType") ExerciseType exerciseType,
                                         @Param("minDuration") double minDuration,
                                         PageRequest pageRequest);

}
