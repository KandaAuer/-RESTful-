package com.example.myproject.service;

import com.example.myproject.model.Student;
import com.example.myproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Получить студентов по возрасту в пределах диапазона
    public List<Student> getStudentsByAgeRange(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    // Получить студентов, чье имя содержит букву
    public List<Student> getStudentsByNameContaining(String letter) {
        return studentRepository.findByNameContainingIgnoreCase(letter);
    }

    // Получить студентов, чей возраст меньше их ID
    public List<Student> getStudentsByAgeLessThanId(Long id) {
        return studentRepository.findByAgeLessThan(id);
    }

    // Получить студентов, отсортированных по возрасту
    public List<Student> getStudentsSortedByAge() {
        return studentRepository.findAllByOrderByAgeAsc();
    }

    // Получить студентов по ID факультета
    public List<Student> getStudentsByFacultyId(Long facultyId) {
        return studentRepository.findByFacultyId(facultyId);
    }
}
