package com.springcourse.activity.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.springcourse.activity.models.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDAO extends CrudRepository<Department, Integer>{
    
}
