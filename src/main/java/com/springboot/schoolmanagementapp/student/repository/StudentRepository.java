package com.springboot.schoolmanagementapp.student.repository;

import com.springboot.schoolmanagementapp.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByFirstnameContaining(String p);
}
