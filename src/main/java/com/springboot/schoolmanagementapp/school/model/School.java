package com.springboot.schoolmanagementapp.school.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springboot.schoolmanagementapp.student.model.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class School {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(
            mappedBy = "school"
    )
    @JsonManagedReference
    private List<Student> students;

    public School(String name) {
        this.name = name;
    }
}
