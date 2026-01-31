package com.springcourse.activity.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springcourse.activity.models.entity.Employee;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee, Integer> {
    
}
