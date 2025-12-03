package com.coding.shuttle.springbootPracticeProject.dto;

import com.coding.shuttle.springbootPracticeProject.annotation.PrimeNumber;
import com.coding.shuttle.springbootPracticeProject.annotation.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    private Long id;

    @NotBlank(message = "Department title should be blank")
    private String title;

    @AssertTrue(message = "Department should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

    private LocalDate createdAt;

    @NotNull(message = "Department Code should be null")
    @PrimeNumber
    private Integer departmentCode;

    @NotNull(message = "password should not be Null")
    @ValidPassword
    private String adminPassword;

    @Range(min = 1, max = 10)
    private Integer floor;

    @CreditCardNumber
    private String CardNumber;

    @URL
    private String website;
}
