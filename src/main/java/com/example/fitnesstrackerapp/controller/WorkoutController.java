package com.example.fitnesstrackerapp.controller;
import com.example.fitnesstrackerapp.dto.WorkoutDTO;
import com.example.fitnesstrackerapp.service.WorkoutService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/workouts")
@Tag(name = "workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/save")
    public void create(@RequestParam Long userId,
                       @RequestParam Long exerciseId) {
        workoutService.save(userId, exerciseId);
    }

    @GetMapping("/find")
    public List<WorkoutDTO> findAll() {
        return workoutService.findAll();
    }

    @PutMapping("/update")
    public void update(@RequestParam Long workoutId,
                       @RequestParam Long exerciseId) {
        workoutService.update(workoutId, exerciseId);
    }
}
