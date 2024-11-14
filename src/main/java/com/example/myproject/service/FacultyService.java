package com.example.myproject.service;

import com.example.myproject.model.Faculty;
import com.example.myproject.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    // Получить факультеты по имени или цвету
    public List<Faculty> getFacultiesByNameOrColor(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(name, color);
    }
}
