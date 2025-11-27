package com.coding.shuttle.springbootPracticeProject.controllers;

import com.coding.shuttle.springbootPracticeProject.dto.EmployeeDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @GetMapping(path = "/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return new EmployeeDto(1l, "Piyush", "piyush@gmail.com", 22, LocalDate.of(2022,03,27), true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false , name = "EmpAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "employee age is " + age + " and sort by " + sortBy;
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employee){
        employee.setId((long)Math.random());
        return employee;
    }

    @PutMapping
    public String updateEmployee(){
        return "Hello from put mapping";
    }

}
