package com.example.myproject.repository;

import com.example.myproject.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    // Метод для поиска факультетов по имени или цвету, игнорируя регистр, с поддержкой частичного совпадения
    List<Faculty> findByNameIgnoreCaseContainingOrColorIgnoreCaseContaining(String name, String color);
}
