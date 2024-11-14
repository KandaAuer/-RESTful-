package com.example.myproject.service;

import com.example.myproject.model.Faculty;

import java.util.HashMap;
import java.util.Map;

public class FacultyService {
    private Map<Long, Faculty> facultyMap = new HashMap<>();
    private Long idCounter = 1L;

    // CRUD-операции

    public Faculty createFaculty(String name, String color) {
        Faculty faculty = new Faculty(idCounter++, name, color);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        return facultyMap.get(id);
    }

    public Faculty updateFaculty(Long id, String name, String color) {
        Faculty faculty = facultyMap.get(id);
        if (faculty != null) {
            faculty.setName(name);
            faculty.setColor(color);
        }
        return faculty;
    }

    public void deleteFaculty(Long id) {
        facultyMap.remove(id);
    }

    public Map<Long, Faculty> getAllFaculties() {
        return facultyMap;
    }

    public Map<Long, Faculty> filterFacultiesByColor(String color) {
        Map<Long, Faculty> filtered = new HashMap<>();
        for (Faculty faculty : facultyMap.values()) {
            if (faculty.getColor().equalsIgnoreCase(color)) {
                filtered.put(faculty.getId(), faculty);
            }
        }
        return filtered;
    }
}
