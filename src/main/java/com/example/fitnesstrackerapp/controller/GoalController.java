package com.example.fitnesstrackerapp.controller;

import com.example.fitnesstrackerapp.dto.GoalDTO;
import com.example.fitnesstrackerapp.dto.UserDTO;
import com.example.fitnesstrackerapp.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    @GetMapping
    private ResponseEntity<List<GoalDTO>> getGoals() {
        List<GoalDTO> goals = goalService.getGoals();
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }
}
