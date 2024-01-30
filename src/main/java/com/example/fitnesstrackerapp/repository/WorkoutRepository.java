package com.example.fitnesstrackerapp.repository;

import com.example.fitnesstrackerapp.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    //return type list of workouts - parameter - userId
//    @Query("select w from Workout w where w.user.id = :userId")
    List<Workout> findByUserId(Long userId);

//    @Query(value = "select * from exercises e where e.min_duration > :minDuration", nativeQuery = true)
//    SELECT * FROM workout where start_time >= '2024-01-16T00:00:00'

    @Query(value = "SELECT w FROM Workout w where w.startTime >= :threshold AND w.user.id = :userId")
    List<Workout> findAllByDays(@Param("threshold") LocalDateTime threshold,
                                @Param("userId") Long userId);

}
