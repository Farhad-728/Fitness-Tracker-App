package com.example.fitnesstrackerapp.controller;
import com.example.fitnesstrackerapp.dto.GoalDTO;
import com.example.fitnesstrackerapp.service.GoalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/goals")
@Tag(name = "goals")
public class GoalController {

    private final GoalService goalService;

    @PostMapping(value = "/save/{userId}")
    private ResponseEntity createGoal(@RequestBody GoalDTO goalDTO, @PathVariable Long userId) {
        goalService.saveGoal(goalDTO, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    private void updateGoal(@RequestBody GoalDTO goalDTO) {
        goalService.updateGoal(goalDTO);
    }
    @GetMapping
    private List<GoalDTO> getGoals() {
        return goalService.getAllGoals();
    }

    @DeleteMapping("/delete/{id}")
    private void deleteGoalById(@PathVariable Long id) {
        goalService.deleteGoalById(id);
    }
}
