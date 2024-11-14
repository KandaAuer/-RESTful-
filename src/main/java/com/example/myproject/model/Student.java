package com.example.myproject.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Идентификатор студента

    private String name;  // Имя студента
    private int age;      // Возраст студента

    @ManyToOne
    @JoinColumn(name = "faculty_id")  // Связь ManyToOne с факультетом
    private Faculty faculty;  // Ссылка на факультет, к которому принадлежит студент

    // Конструктор по умолчанию
    public Student() {}

    // Конструктор с параметрами
    public Student(String name, int age, Faculty faculty) {
        this.name = name;
        this.age = age;
        this.faculty = faculty;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
