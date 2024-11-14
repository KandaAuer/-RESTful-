package com.example.myproject.repository;

import com.example.myproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Метод для поиска студентов по возрасту в пределах диапазона
    List<Student> findByAgeBetween(int minAge, int maxAge);

    // Метод для поиска студентов, чье имя содержит определенную букву (например, "О")
    List<Student> findByNameContainingIgnoreCase(String letter);

    // Метод для поиска студентов, чей возраст меньше их ID
    List<Student> findByAgeLessThan(Long id);

    // Метод для получения студентов, отсортированных по возрасту
    List<Student> findAllByOrderByAgeAsc();

    // Метод для поиска студентов по факультету (по его ID)
    List<Student> findByFacultyId(Long facultyId);
}
