package com.example.myproject;

import com.example.myproject.controller.StudentController;
import com.example.myproject.model.Student;
import com.example.myproject.service.StudentService;
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

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testGetStudents() throws Exception {
        Mockito.when(studentService.getAllStudents())
                .thenReturn(List.of(new Student(1L, "Ivan", "Ivanov", 20)));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Ivan")))
                .andExpect(jsonPath("$[0].lastName", is("Ivanov")));
    }

    @Test
    public void testGetStudentById() throws Exception {
        Student student = new Student(1L, "Ivan", "Ivanov", 20);
        Mockito.when(studentService.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Ivan")))
                .andExpect(jsonPath("$.lastName", is("Ivanov")));
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student newStudent = new Student(null, "Petr", "Petrov", 18);
        Mockito.when(studentService.createStudent(Mockito.any(Student.class)))
                .thenReturn(new Student(1L, "Petr", "Petrov", 18));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Petr\",\"lastName\":\"Petrov\",\"age\":18}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Petr")))
                .andExpect(jsonPath("$.lastName", is("Petrov")));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student updatedStudent = new Student(1L, "Alex", "Ivanov", 21);
        Mockito.when(studentService.updateStudent(Mockito.anyLong(), Mockito.any(Student.class)))
                .thenReturn(updatedStudent);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Alex\",\"lastName\":\"Ivanov\",\"age\":21}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Alex")))
                .andExpect(jsonPath("$.age", is(21)));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Mockito.doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
