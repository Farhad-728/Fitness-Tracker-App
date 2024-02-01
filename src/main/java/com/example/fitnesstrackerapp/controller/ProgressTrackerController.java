package com.example.fitnesstrackerapp.controller;
import com.example.fitnesstrackerapp.entity.ProgressTracker;
import com.example.fitnesstrackerapp.service.ProgressTrackerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/tracker")
@Tag(name = "trackers")
public class ProgressTrackerController {

    private final ProgressTrackerService trackerService;

    @GetMapping("/progress/{userId}")
    public ResponseEntity<ProgressTracker> getProgress(@PathVariable Long userId,
                                                        @RequestParam Long numberOfDays) {
        ProgressTracker progressTracker = trackerService.getProgress(userId, numberOfDays);
        return new ResponseEntity<>(progressTracker, HttpStatus.OK);
    }
}
