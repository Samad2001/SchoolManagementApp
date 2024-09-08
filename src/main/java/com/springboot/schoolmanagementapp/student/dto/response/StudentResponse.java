package com.springboot.schoolmanagementapp.student.dto.response;

public record StudentResponse(
        String firstName,
        String lastName,
        String email
) {
}
