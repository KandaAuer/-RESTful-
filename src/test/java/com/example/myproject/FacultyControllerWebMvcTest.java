package com.example.myproject;

import com.example.myproject.controller.FacultyController;
import com.example.myproject.model.Faculty;
import com.example.myproject.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void testGetFaculties() throws Exception {
        Mockito.when(facultyService.getAllFaculties())
                .thenReturn(List.of(new Faculty(1L, "Faculty of IT")));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Faculty of IT")));
    }

    @Test
    public void testGetFacultyById() throws Exception {
        Faculty faculty = new Faculty(1L, "Faculty of IT");
        Mockito.when(facultyService.getFacultyById(1L)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculties/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Faculty of IT")));
    }

    @Test
    public void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty(null, "New Faculty");
        Mockito.when(facultyService.createFaculty(Mockito.any(Faculty.class)))
                .thenReturn(new Faculty(1L, "New Faculty"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Faculty\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("New Faculty")));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Faculty updatedFaculty = new Faculty(1L, "Updated Faculty");
        Mockito.when(facultyService.updateFaculty(Mockito.anyLong(), Mockito.any(Faculty.class)))
                .thenReturn(updatedFaculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculties/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Faculty\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Faculty")));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Mockito.doNothing().when(facultyService).deleteFaculty(1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculties/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
