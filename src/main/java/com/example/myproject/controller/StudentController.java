package com.example.myproject.controller;

import com.example.myproject.service.StudentService;
import com.example.myproject.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Эндпоинт для получения аватарки по ID
    @GetMapping("/{id}/avatar")
    public byte[] getAvatar(@PathVariable Long id) {
        try {
            return studentService.getAvatar(id);
        } catch (IOException e) {
            e.printStackTrace();
            return null;  // Можно вернуть ошибку или другое сообщение, если файл не найден
        }
    }

    // Эндпоинт для обновления аватарки студента
    @PostMapping("/{id}/avatar")
    public ResponseEntity<Void> updateAvatar(@PathVariable Long id, @RequestParam("avatar") MultipartFile file) {
        try {
            studentService.updateAvatar(id, file);
            return ResponseEntity.ok().build();  // Возвращаем успешный ответ
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();  // Возвращаем ошибку 500 в случае исключения
        }
    }

    // Эндпоинт для получения всех студентов
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Эндпоинт для получения студента по ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Эндпоинт для создания нового студента
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    // Эндпоинт для обновления информации о студенте
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // Эндпоинт для удаления студента
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
