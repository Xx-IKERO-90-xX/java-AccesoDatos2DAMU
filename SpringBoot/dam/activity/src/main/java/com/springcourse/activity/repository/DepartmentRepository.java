package com.springcourse.activity.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springcourse.activity.models.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    
}
