package com.example.myproject.controller;

import com.example.myproject.model.Student;
import com.example.myproject.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService = new StudentService();

    // СОЗДАВАТЬ
    @PostMapping
    public Student createStudent(@RequestParam String name, @RequestParam int age) {
        return studentService.createStudent(name, age);
    }

    // ЧИТАТЬ
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    // ОБНОВЛЯТЬ
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestParam String name, @RequestParam int age) {
        return studentService.updateStudent(id, name, age);
    }

    // УДАЛИТЬ
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // ФИЛЬТРАТЬ по возрасту
    @GetMapping("/filter")
    public Map<Long, Student> filterStudents(@RequestParam int age) {
        return studentService.filterStudentsByAge(age);
    }

    // ПОЛУЧИТЬ ВСЕ
    @GetMapping
    public Map<Long, Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
