package com.cse.academicinfosystem.courses.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year_of_study")
    private Long year;

    private Long semester;

    private String code;

    private String name;

    private String type;

    private String professor;

    private Long credits;
}
