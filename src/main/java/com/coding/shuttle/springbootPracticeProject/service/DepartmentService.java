package com.coding.shuttle.springbootPracticeProject.service;

import com.coding.shuttle.springbootPracticeProject.dto.DepartmentDto;
import com.coding.shuttle.springbootPracticeProject.entities.DepartmentEntity;
import com.coding.shuttle.springbootPracticeProject.exceptions.ResourceNotFoundException;
import com.coding.shuttle.springbootPracticeProject.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    public List<DepartmentDto> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities.stream()
                .map(departmentEntity -> mapper.map(departmentEntity, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<DepartmentDto> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .map(departmentEntity -> ResponseEntity.ok(mapper.map(departmentEntity, DepartmentDto.class)))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id "+departmentId));
    }

    public ResponseEntity<DepartmentDto> createDepartment(DepartmentDto departmentDto) {
        DepartmentEntity toBeSavedEntity = mapper.map(departmentDto, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(toBeSavedEntity);
        return new ResponseEntity<>(mapper.map(savedEntity, DepartmentDto.class), HttpStatus.CREATED);
    }

    public ResponseEntity<DepartmentDto> updateDepartment(DepartmentDto departmentDto, Long departmentId) {
        isDepartmentExists(departmentId);
        departmentDto.setId(departmentId);
        DepartmentEntity tobeSavedEntity = mapper.map(departmentDto, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(tobeSavedEntity);
        return ResponseEntity.ok(mapper.map(savedEntity, DepartmentDto.class));
    }
    private boolean isDepartmentExists(Long departmentId){
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department Not Found with id "+departmentId);
        return true;
    }

    public DepartmentDto updatePartialDepartment(Long departmentId, Map<String, Object> department) {
        isDepartmentExists(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        department.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(DepartmentEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
        } );
        return mapper.map(departmentRepository.save(departmentEntity), DepartmentDto.class);
    }

    public ResponseEntity<Boolean> deleteDepartment(Long departmentId) {
        isDepartmentExists(departmentId);
        departmentRepository.deleteById(departmentId);
        return ResponseEntity.ok(true);
    }
}
