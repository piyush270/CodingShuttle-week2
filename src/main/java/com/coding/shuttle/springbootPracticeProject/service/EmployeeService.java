package com.coding.shuttle.springbootPracticeProject.service;

import com.coding.shuttle.springbootPracticeProject.dto.EmployeeDto;
import com.coding.shuttle.springbootPracticeProject.entities.EmployeeEntity;
import com.coding.shuttle.springbootPracticeProject.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper){
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }
    public Optional<EmployeeDto> findById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.map(employeeEntity1 -> mapper.map(employeeEntity1,EmployeeDto.class));
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

    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long id) {
        EmployeeEntity toBeSavedEntity = mapper.map(employeeDto, EmployeeEntity.class);
        toBeSavedEntity.setId(id);
        EmployeeEntity savedEntity = employeeRepository.save(toBeSavedEntity);
        return mapper.map(savedEntity, EmployeeDto.class);
    }

    public Boolean deleteEmployeeById(EmployeeDto employeeDto, Long id) {
        boolean isexists = isExists(id);
        if(!isexists) return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDto updatePartialEmployeeById(Long id, Map<String, Object> employee) {
        boolean isExists = isExists(id);
        if(!isExists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employee.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);

        });

        return mapper.map(employeeRepository.save(employeeEntity), EmployeeDto.class);
    }

    private Boolean isExists(Long id){
        return employeeRepository.existsById(id);
    }
}
