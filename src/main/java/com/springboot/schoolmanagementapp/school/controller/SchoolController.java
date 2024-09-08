package com.springboot.schoolmanagementapp.school.controller;

import com.springboot.schoolmanagementapp.school.dto.SchoolDto;
import com.springboot.schoolmanagementapp.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/schools")
    public List<SchoolDto> getSchools(){
        return schoolService.getSchools();
    }

    @PostMapping("/addSchool")
    public SchoolDto create(
            @RequestBody SchoolDto dto) {
        return schoolService.addSchool(dto);
    }


}
