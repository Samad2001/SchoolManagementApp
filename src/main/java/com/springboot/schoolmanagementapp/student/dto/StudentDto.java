package com.springboot.schoolmanagementapp.student.dto;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "Firstname  should not be empty")
        String firstName,
        @NotEmpty(message = "Lastname  should not be empty")
        String lastName,
        String email,
        Long schoolId
) {
}
