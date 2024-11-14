package com.example.myproject.service;

import com.example.myproject.model.Faculty;
import com.example.myproject.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(String name, String color) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFaculty(Long id) {
        return facultyRepository.findById(id);
    }

    public Faculty updateFaculty(Long id, String name, String color) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public List<Faculty> filterFacultiesByColor(String color) {
        return facultyRepository.findByColor(color);
    }
}
