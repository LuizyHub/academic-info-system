package com.cse.academicinfosystem.courses.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class CourseAddDto {
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String professor;

    @NotNull
    @Range(min = 1, max = 5)
    private Long credits;
}
