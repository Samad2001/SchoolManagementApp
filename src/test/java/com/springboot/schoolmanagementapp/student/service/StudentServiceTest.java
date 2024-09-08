package com.springboot.schoolmanagementapp.student.service;

import com.springboot.schoolmanagementapp.student.dto.StudentDto;
import com.springboot.schoolmanagementapp.student.dto.response.StudentResponse;
import com.springboot.schoolmanagementapp.student.mapper.StudentMapper;
import com.springboot.schoolmanagementapp.student.model.Student;
import com.springboot.schoolmanagementapp.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper mapper;
    @Mock
    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void should_successfully_create_a_student() {
        StudentDto dto = new StudentDto(
                "Murad",
                "Ali",
                "m.ali@gmail.com",
                1L
        );

        Student student = new Student(
                "Murad",
                "Ali",
                "m.ali@gmail.com",
                24
        );


        Student savedStudent = new Student(
                "Murad",
                "Ali",
                "m.ali@gmail.com",
                24
        );
        savedStudent.setId(1L);

        when(mapper.toStudent(dto))
                .thenReturn(student);

        when(repository.save(student))
                .thenReturn(savedStudent);

        when(mapper.toStudentResponse(savedStudent))
                .thenReturn(new StudentResponse(
                        "Murad",
                        "Ali",
                        "m.ali@gmail.com"));

        StudentResponse response = studentService.saveStudent(dto);

        assertEquals(dto.firstName(), response.firstName());
        assertEquals(dto.lastName(), response.lastName());
        assertEquals(dto.email(), response.email());

        verify(mapper, times(1))
                .toStudent(dto);
        verify(repository, times(1))
                .save(student);
        verify(mapper, times(1))
                .toStudentResponse(savedStudent);
    }

    @Test
    public void shouldReturnAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Murad",
                "Ali",
                "m.ali@gmail.com",
                24
        ));

        //Mock the calls
        when(repository.findAll()).thenReturn(students);
        when(mapper.toStudentResponse(any(Student.class)))
                .thenReturn(new StudentResponse(
                        "Murad",
                        "Ali",
                        "m.ali@gmail.com")
                );

        //when
        List<StudentResponse> responses = studentService.findAllStudents();

        //Then
        assertEquals(students.size(), responses.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void shouldReturnStudentById() {
        Long studentId = 1L;
        Student student = new Student(
                "Murad",
                "Ali",
                "m.ali@gmail.com",
                24
        );

        when(repository.findById(studentId))
                .thenReturn(Optional.of(student));

        when(mapper.toStudentResponse(any(Student.class)))
                .thenReturn(new StudentResponse(
                        "Murad",
                        "Ali",
                        "m.ali@gmail.com")
                );

        StudentResponse response = studentService.findStudentById(studentId);

        assertEquals(response.firstName(), student.getFirstname());
        assertEquals(response.lastName(), student.getLastname());
        assertEquals(response.email(), student.getEmail());

        verify(repository, times(1)).findById(studentId);
    }

    @Test
    public void shouldReturnStudentByName() {
        String studentName = "Murad";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Murad",
                "Ali",
                "m.ali@gmail.com",
                24
        ));

        when(repository.findAllByFirstnameContaining(studentName))
                    .thenReturn(students);
        when(mapper.toStudentResponse(any(Student.class)))
                .thenReturn(new StudentResponse(
                        "Murad",
                        "Ali",
                        "m.ali@gmail.com")
                );

        //When
        var response = studentService.findStudentsByName(same(studentName));

        //Then
        assertEquals(students.size(),response.size());
        verify(repository, times(1))
                .findAllByFirstnameContaining(studentName);
    }


}