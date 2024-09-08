package com.springboot.schoolmanagementapp.student.mapper;

import com.springboot.schoolmanagementapp.student.dto.StudentDto;
import com.springboot.schoolmanagementapp.student.dto.response.StudentResponse;
import com.springboot.schoolmanagementapp.student.model.Student;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMappStudentDtoToStudent() {
        StudentDto dto = new StudentDto(
                "John",
                "Mickhel",
                "john@gmail.com",
                1L);

        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstName(), student.getFirstname());
        assertEquals(dto.lastName(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }


    @Test
    public void  shouldThrowNullPointerExceptionWheStudentDtoNull() {
      var msg = assertThrows(NullPointerException.class, ()->mapper.toStudent(null));
      assertEquals("Student DTO should not be null ", msg.getMessage());
    }

    @Test
    public void shouldMapStudentResponse() {
        Student student = new Student(
                "Alex",
                "Sanchez",
                "alex@gmail.com",
                29);

        StudentResponse response = mapper.toStudentResponse(student);
        assertEquals(response.firstName(), student.getFirstname());
        assertEquals(response.lastName(), student.getLastname());
        assertEquals(response.email(), student.getEmail());
    }

}