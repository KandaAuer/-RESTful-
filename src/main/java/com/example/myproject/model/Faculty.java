package com.example.myproject.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // Название факультета
    private String color;  // Цвет факультета

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Student> students; // Список студентов, относящихся к этому факультету

    // Конструктор по умолчанию
    public Faculty() {}

    // Конструктор с параметрами
    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
