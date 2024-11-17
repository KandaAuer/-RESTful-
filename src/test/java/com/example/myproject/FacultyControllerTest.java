package com.example.myproject;

import com.example.myproject.model.Faculty;
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
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/faculties";
    }

    @Test
    public void testGetAllFaculties() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(getBaseUrl(), Faculty[].class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetFacultyById() {
        long id = 1;  // предполагается, что факультет с ID=1 существует
        ResponseEntity<Faculty> response = restTemplate.getForEntity(getBaseUrl() + "/" + id, Faculty.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateFaculty() {
        Faculty newFaculty = new Faculty(null, "New Faculty");

        URI location = restTemplate.postForLocation(getBaseUrl(), newFaculty);
        assertNotNull(location);
    }

    @Test
    public void testUpdateFaculty() {
        long id = 1;  // предполагается, что факультет с ID=1 существует
        Faculty updatedFaculty = new Faculty(null, "Updated Faculty");

        restTemplate.put(getBaseUrl() + "/" + id, updatedFaculty);

        Faculty faculty = restTemplate.getForObject(getBaseUrl() + "/" + id, Faculty.class);
        assertNotNull(faculty);
        assertEquals("Updated Faculty", faculty.getName());
    }

    @Test
    public void testDeleteFaculty() {
        long idToDelete = 2;  // предполагается, что факультет с ID=2 существует

        restTemplate.delete(getBaseUrl() + "/" + idToDelete);

        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restTemplate.getForEntity(getBaseUrl() + "/" + idToDelete, Faculty.class);
        });
    }
}
