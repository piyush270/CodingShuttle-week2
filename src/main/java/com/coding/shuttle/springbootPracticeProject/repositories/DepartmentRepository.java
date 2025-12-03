package com.coding.shuttle.springbootPracticeProject.repositories;

import com.coding.shuttle.springbootPracticeProject.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
