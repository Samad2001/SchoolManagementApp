package com.springboot.schoolmanagementapp.student.mapper;

import com.springboot.schoolmanagementapp.school.model.School;
import com.springboot.schoolmanagementapp.student.model.Student;
import com.springboot.schoolmanagementapp.student.dto.StudentDto;
import com.springboot.schoolmanagementapp.student.dto.response.StudentResponse;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {


    public Student toStudent(StudentDto dto) {
        if (dto == null) {
            throw   new NullPointerException("Student DTO should not be null");
        }

        var student = new Student();
        student.setFirstname(dto.firstName());
        student.setLastname(dto.lastName());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);

        return student;
    }

    public StudentResponse toStudentResponse(Student student) {
        return new StudentResponse(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }
}
