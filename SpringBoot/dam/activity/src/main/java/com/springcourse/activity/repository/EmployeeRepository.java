package com.springcourse.activity.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springcourse.activity.models.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
