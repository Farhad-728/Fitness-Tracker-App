package com.example.fitnesstrackerapp.controller;


import com.example.fitnesstrackerapp.dto.ExerciseDTO;
import com.example.fitnesstrackerapp.enums.ExerciseType;
import com.example.fitnesstrackerapp.service.ExerciseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/exercises")
@Tag(name = "exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/save")
    public void createExercise(@RequestBody ExerciseDTO exerciseDTO) {
        exerciseService.save(exerciseDTO);
    }

    @GetMapping("/findAll")
    public Page<ExerciseDTO> findAll(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "pageSize", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "duration"));
        PageRequest pageRequest = PageRequest.of(offset, size, sort);
        return exerciseService.findAll(pageRequest);
    }

    @GetMapping("/searchByName")
    public Page<ExerciseDTO> searchByName(@RequestParam(name = "name") String name,
                                          @RequestParam(name = "offset", defaultValue = "0") int offset,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(offset, size);
        return exerciseService.findByName(name, pageRequest);
    }


    @GetMapping("/filterByType")
    public Page<ExerciseDTO> filterByType(@RequestParam(name = "type") ExerciseType type,
                                          @RequestParam(name = "offset", defaultValue = "0") int offset,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(offset, size);
        return exerciseService.filterByType(type, pageRequest);
    }
    @GetMapping("/filterByDuration")
    public Page<ExerciseDTO> filterByDuration(@RequestParam(name = "duration") double duration,
                                              @RequestParam(name = "offset", defaultValue = "0") int offset,
                                              @RequestParam(name = "pageSize", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(offset, size);
        return exerciseService.filterByDuration(duration, pageRequest);
    }
}
