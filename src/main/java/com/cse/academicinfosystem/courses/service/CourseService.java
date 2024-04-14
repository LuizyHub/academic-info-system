package com.cse.academicinfosystem.courses.service;

import com.cse.academicinfosystem.courses.domain.Course;
import com.cse.academicinfosystem.courses.dto.CourseAddDto;
import com.cse.academicinfosystem.courses.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private static final Long NEXT_SEMESTER_YEAR = 2024L;
    private static final Long NEXT_SEMESTER = 2L;

    private final CourseRepository courseRepository;

    public List<Course> findByYearAndSemester(Long year, Long semester) {
        return courseRepository.findByYearAndSemester(year, semester);
    }

    public List<Course> findAllCreditsSum() {
        List<Course> courses = courseRepository.findAll();

        List<Course> result = new ArrayList<>();

        loop :
        for (Course course : courses) {
            long courseYear = course.getYear();
            long courseSemester = course.getSemester();

            if (courseYear >= NEXT_SEMESTER_YEAR && courseSemester >= NEXT_SEMESTER) {
                continue;
            }

            for (Course resultCourse : result) {
                if (resultCourse.getYear().equals(courseYear) && resultCourse.getSemester().equals(courseSemester)) {
                    resultCourse.setCredits(resultCourse.getCredits() + course.getCredits());
                    continue loop;
                }
            }
            result.add(course);
        }

        result.sort((o1, o2) -> {
            if (o1.getYear().equals(o2.getYear())) {
                return o1.getSemester().compareTo(o2.getSemester());
            }
            return o1.getYear().compareTo(o2.getYear());
        });

        return result;
    }

    public void save(CourseAddDto courseAddDto) {
        Course course = new Course();

        course.setYear(NEXT_SEMESTER_YEAR);
        course.setSemester(NEXT_SEMESTER);
        course.setCode(courseAddDto.getCode());
        course.setName(courseAddDto.getName());
        course.setType(courseAddDto.getType());
        course.setCredits(courseAddDto.getCredits());

        courseRepository.save(course);
    }
}
