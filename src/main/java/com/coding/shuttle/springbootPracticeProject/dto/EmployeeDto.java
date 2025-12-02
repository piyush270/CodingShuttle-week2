package com.coding.shuttle.springbootPracticeProject.dto;

import com.coding.shuttle.springbootPracticeProject.annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    @NotBlank(message = "Employee name cannot be blank")
    @Size(min = 3, max = 10, message = "Name of the employe should be in the range [3,10]")
    private String name;

    @NotBlank(message = "email cannot be blank or null")
    @Email(message = "email should be in valid email format")
    private String email;

    @NotNull(message = "age cannot be null or empty")
    @Max(value = 70, message = "age of employee cannot be greater than 70")
    @Min(value = 18, message = "age of employee cannot be less than 18")
    private Integer age;

    @NotBlank(message = "role of an employee cannot be null or blank")
    //@Pattern(regexp = "^(ADMIN|USER)$", message = "Role of an employee is either ADMIN or USER")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "salary of an employee cannot be null or blank")
    @Positive(message = "Salary of an employee cannot be negative")
    @Digits(integer = 6, fraction = 2, message = "salary of an employee should be in the form XXXXXX.YY")
    @DecimalMax(value = "900000.99")
    @DecimalMin(value = "1000.00")
    private Double salary;

    @PastOrPresent(message = "DateOfJoining of an employee cannot be in the future")
    private LocalDate dateOfJoining;

    @AssertTrue
    @JsonProperty("isActive")
    private Boolean isActive;
}
