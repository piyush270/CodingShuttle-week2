package com.coding.shuttle.springbootPracticeProject.repositories;

import com.coding.shuttle.springbootPracticeProject.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
