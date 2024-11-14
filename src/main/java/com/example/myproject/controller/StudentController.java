package com.example.myproject.controller;

import com.example.myproject.model.Student;
import com.example.myproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Эндпоинт для получения студентов по возрасту в пределах диапазона
    @GetMapping("/age")
    public List<Student> getStudentsByAgeRange(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.getStudentsByAgeRange(minAge, maxAge);
    }

    // Эндпоинт для получения студентов по букве в имени
    @GetMapping("/name")
    public List<Student> getStudentsByNameContaining(@RequestParam String letter) {
        return studentService.getStudentsByNameContaining(letter);
    }

    // Эндпоинт для получения студентов, чей возраст меньше их ID
    @GetMapping("/age-less-than-id/{id}")
    public List<Student> getStudentsByAgeLessThanId(@PathVariable Long id) {
        return studentService.getStudentsByAgeLessThanId(id);
    }

    // Эндпоинт для получения студентов, отсортированных по возрасту
    @GetMapping("/sorted-by-age")
    public List<Student> getStudentsSortedByAge() {
        return studentService.getStudentsSortedByAge();
    }

    // Эндпоинт для получения студентов по факультету
    @GetMapping("/faculty/{facultyId}")
    public List<Student> getStudentsByFacultyId(@PathVariable Long facultyId) {
        return studentService.getStudentsByFacultyId(facultyId);
    }
}
