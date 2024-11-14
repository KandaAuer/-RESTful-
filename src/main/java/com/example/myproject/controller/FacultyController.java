package com.example.myproject.controller;

import com.example.myproject.model.Faculty;
import com.example.myproject.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService = new FacultyService();

    // СОЗДАВАТЬ
    @PostMapping
    public Faculty createFaculty(@RequestParam String name, @RequestParam String color) {
        return facultyService.createFaculty(name, color);
    }

    // ЧИТАТЬ
    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }

    // ОБНОВЛЯТЬ
    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestParam String name, @RequestParam String color) {
        return facultyService.updateFaculty(id, name, color);
    }

    // УДАЛИТЬ
    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    // ФИЛЬТР по цвету
    @GetMapping("/filter")
    public Map<Long, Faculty> filterFaculties(@RequestParam String color) {
        return facultyService.filterFacultiesByColor(color);
    }

    // ПОЛУЧИТЬ ВСЕ
    @GetMapping
    public Map<Long, Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }
}
