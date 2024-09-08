package com.springboot.schoolmanagementapp.student.service;

import com.springboot.schoolmanagementapp.student.dto.StudentDto;
import com.springboot.schoolmanagementapp.student.dto.response.StudentResponse;
import com.springboot.schoolmanagementapp.student.mapper.StudentMapper;
import com.springboot.schoolmanagementapp.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponse saveStudent(StudentDto dto) {
        var student = studentMapper.toStudent(dto);
        var saveStudent = studentRepository.save(student);
        return studentMapper.toStudentResponse(saveStudent);
    }

    public List<StudentResponse> findAllStudents() {
      return studentRepository.findAll()
              .stream()
              .map(studentMapper::toStudentResponse)
              .collect(Collectors.toList());
    }

    public StudentResponse findStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponse)
                .orElse(null);
    }

    public List<StudentResponse> findStudentsByName(String name) {
        return studentRepository.findAllByFirstnameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
