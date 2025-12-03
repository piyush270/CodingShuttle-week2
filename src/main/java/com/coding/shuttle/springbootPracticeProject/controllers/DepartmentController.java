package com.coding.shuttle.springbootPracticeProject.controllers;

import com.coding.shuttle.springbootPracticeProject.dto.DepartmentDto;
import com.coding.shuttle.springbootPracticeProject.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "departmentId") Long id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid DepartmentDto departmentDto){
        return departmentService.createDepartment(departmentDto);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody @Valid DepartmentDto departmentDto, @PathVariable(name = "departmentId") Long id){
         return departmentService.updateDepartment(departmentDto, id);
    }

    @PatchMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updatePartialDepartment(@PathVariable(name = "departmentId") Long id, @RequestBody Map<String, Object> department){
       return  ResponseEntity.ok(departmentService.updatePartialDepartment(id,department));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable(name = "departmentId") Long id){
        return departmentService.deleteDepartment(id);
    }


}
