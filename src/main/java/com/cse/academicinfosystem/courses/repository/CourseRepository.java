package com.cse.academicinfosystem.courses.repository;

import com.cse.academicinfosystem.courses.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByYearAndSemester(Long year, Long semester);
}
