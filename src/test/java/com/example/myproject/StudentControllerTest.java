package com.example.myproject;

import com.example.myproject.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/students";
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(getBaseUrl(), Student[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetStudentById() {
        long id = 1;
        ResponseEntity<Student> response = restTemplate.getForEntity(getBaseUrl() + "/" + id, Student.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateStudent() {
        Student newStudent = new Student(null, "Petr", "Petrov", 18);

        URI location = restTemplate.postForLocation(getBaseUrl(), newStudent);
        assertNotNull(location);
    }

    @Test
    public void testUpdateStudent() {
        long id = 1;
        Student updatedStudent = new Student(null, "Alex", "Ivanov", 21);

        restTemplate.put(getBaseUrl() + "/" + id, updatedStudent);

        Student student = restTemplate.getForObject(getBaseUrl() + "/" + id, Student.class);
        assertNotNull(student);
        assertEquals("Alex", student.getFirstName());
    }

    @Test
    public void testDeleteStudent() {
        long idToDelete = 2;

        restTemplate.delete(getBaseUrl() + "/" + idToDelete);

        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restTemplate.getForEntity(getBaseUrl() + "/" + idToDelete, Student.class);
        });
    }
}
