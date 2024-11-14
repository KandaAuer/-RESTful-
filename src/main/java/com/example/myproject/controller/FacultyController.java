package com.example.myproject.controller;

import com.example.myproject.model.Faculty;
import com.example.myproject.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // Эндпоинт для получения факультетов по имени или цвету
    @GetMapping("/search")
    public List<Faculty> getFacultiesByNameOrColor(@RequestParam String name, @RequestParam String color) {
        return facultyService.getFacultiesByNameOrColor(name, color);
    }
}
