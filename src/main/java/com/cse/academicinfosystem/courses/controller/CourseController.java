package com.cse.academicinfosystem.courses.controller;

import com.cse.academicinfosystem.courses.domain.Course;
import com.cse.academicinfosystem.courses.dto.CourseAddDto;
import com.cse.academicinfosystem.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public String courses(Model model) {
        List<Course> courses = courseService.findAllCreditsSum();
        model.addAttribute("courses", courses);
        long sum = courses.stream().mapToLong(Course::getCredits).sum();
        model.addAttribute("sum", sum);
        return "courses";
    }

    @GetMapping("/{year}/{semester}")
    public String coursesByYearAndSemester(@PathVariable Long year, @PathVariable Long semester, Model model) {
        List<Course> courses = courseService.findByYearAndSemester(year, semester);
        model.addAttribute("courses", courses);
        return "coursesDetails";
    }

    @GetMapping("/add")
    public String addCourseForm(@ModelAttribute("course") Course course) {
        return "registerCourse";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute("course") @Validated CourseAddDto course,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerCourse";
        }
        courseService.save(course);
        return "redirect:/courses";
    }

}