package com.coding.shuttle.springbootPracticeProject.service;

import com.coding.shuttle.springbootPracticeProject.dto.EmployeeDto;
import com.coding.shuttle.springbootPracticeProject.entities.EmployeeEntity;
import com.coding.shuttle.springbootPracticeProject.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper){
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }
    public EmployeeDto findById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return mapper.map(employeeEntity, EmployeeDto.class);
    }

    public List<EmployeeDto> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> mapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto save(EmployeeDto employee) {
        EmployeeEntity inputEntity = mapper.map(employee, EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(inputEntity);
        return mapper.map(savedEntity, EmployeeDto.class);
    }
}
