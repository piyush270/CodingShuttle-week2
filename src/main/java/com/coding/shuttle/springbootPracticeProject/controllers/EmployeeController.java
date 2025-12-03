package com.coding.shuttle.springbootPracticeProject.controllers;

import com.coding.shuttle.springbootPracticeProject.dto.EmployeeDto;
import com.coding.shuttle.springbootPracticeProject.exceptions.ResourceNotFoundException;
import com.coding.shuttle.springbootPracticeProject.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDto> employeeDto = employeeService.findById(id);
        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Found with id "+id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestParam(required = false, name = "EmpAge") Integer age,
                                                             @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@Valid @RequestBody EmployeeDto employeeDto, @PathVariable(name = "employeeId") Long id) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDto, id));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@RequestBody EmployeeDto employeeDto, @PathVariable(name = "employeeId") Long id) {
        Boolean isDeleted = employeeService.deleteEmployeeById(employeeDto, id);
        if (!isDeleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(true);
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updatePartialEmployeeById(@PathVariable(name = "employeeId") Long id, @RequestBody Map<String, Object> employee) {
        EmployeeDto employeeDto = employeeService.updatePartialEmployeeById(id, employee);
        if (employeeDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDto);
    }
}
