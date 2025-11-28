package com.coding.shuttle.springbootPracticeProject.controllers;

import com.coding.shuttle.springbootPracticeProject.dto.EmployeeDto;
import com.coding.shuttle.springbootPracticeProject.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeService.findById(id);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees(@RequestParam(required = false , name = "EmpAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.findAll();
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employee){
        return employeeService.save(employee);
    }

    @PutMapping
    public String updateEmployee(){
        return "Hello from put mapping";
    }

}
