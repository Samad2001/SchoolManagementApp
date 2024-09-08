package com.springboot.schoolmanagementapp.student.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.schoolmanagementapp.studentprofile.StudentProfile;
import com.springboot.schoolmanagementapp.school.model.School;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_STUDENT")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @Column(
            name = "c_fname",
            length = 20
    )
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private int age;
    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(
            name = "school_id"
    )
    @JsonBackReference
    private School school;


    public Student(String alex, String sanchez, String mail, int i) {
    }
}
