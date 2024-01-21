package com.example.fitnesstrackerapp.service;

import com.example.fitnesstrackerapp.entity.ProgressTracker;

public interface ProgressTrackerService {
   ProgressTracker getProgress(Long userId);
}
