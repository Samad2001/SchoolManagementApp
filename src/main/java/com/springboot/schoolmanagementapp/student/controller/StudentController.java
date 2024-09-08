package com.springboot.schoolmanagementapp.student.controller;

import com.springboot.schoolmanagementapp.student.service.StudentService;
import com.springboot.schoolmanagementapp.student.dto.StudentDto;
import com.springboot.schoolmanagementapp.student.dto.response.StudentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{by-id}")
    public StudentResponse getStudentById(
            @PathVariable("by-id") Long id) {
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponse> getStudentByName(
            @PathVariable("student-name") String name) {
        return studentService.findStudentsByName(name);
    }

    @PostMapping("/addStudent")
    public StudentResponse saveStudent(
            @Valid @RequestBody StudentDto dto
    ) {
        return studentService.saveStudent(dto);
    }


    @DeleteMapping("/students/delete/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
            @PathVariable("student-id") Long id) {
        studentService.delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException  exp
    ){
        var errors = new HashMap<String, String >();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
