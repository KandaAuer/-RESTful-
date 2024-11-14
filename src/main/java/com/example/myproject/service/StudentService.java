package com.example.myproject.service;

import com.example.myproject.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private Map<Long, Student> studentMap = new HashMap<>();
    private Long idCounter = 1L;

    // CRUD-операции

    public Student createStudent(String name, int age) {
        Student student = new Student(idCounter++, name, age);
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student getStudent(Long id) {
        return studentMap.get(id);
    }

    public Student updateStudent(Long id, String name, int age) {
        Student student = studentMap.get(id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
        }
        return student;
    }

    public void deleteStudent(Long id) {
        studentMap.remove(id);
    }

    public Map<Long, Student> getAllStudents() {
        return studentMap;
    }

    public Map<Long, Student> filterStudentsByAge(int age) {
        Map<Long, Student> filtered = new HashMap<>();
        for (Student student : studentMap.values()) {
            if (student.getAge() == age) {
                filtered.put(student.getId(), student);
            }
        }
        return filtered;
    }
}
