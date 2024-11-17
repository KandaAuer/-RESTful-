package com.example.myproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name should not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name should not exceed 50 characters")
    private String lastName;

    @NotNull(message = "Age is required")
    @Min(17)
    private int age;

    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private Avatar avatar;

    public Student(Object o, String alex, String ivanov, int i) {
    }

    public String getFirstName() {
        return null;
    }
}
