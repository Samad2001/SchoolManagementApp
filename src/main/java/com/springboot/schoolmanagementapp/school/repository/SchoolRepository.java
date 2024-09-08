package com.springboot.schoolmanagementapp.school.repository;


import com.springboot.schoolmanagementapp.school.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository  extends JpaRepository<School, Long> {
}
