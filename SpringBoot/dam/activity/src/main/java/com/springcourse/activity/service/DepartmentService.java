package com.springcourse.activity.service;
import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.springcourse.activity.models.dao.DepartmentDAO;
import com.springcourse.activity.models.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentDAO departmentDAO;

    public List<Department> findAll() {
        return (List<Department>) departmentDAO.findAll();
    }

    public Department findById(int id) {
        Optional<Department> department = departmentDAO.findById(id);

        if (!department.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return department.get();
    }

    public Department saveDepartment(Department department) {
        return departmentDAO.save(department);
    }

    public Department updateDepartment(Department updatedDepartment) {
        Department currentDepartment = departmentDAO.findById(updatedDepartment.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found!"));
        
        currentDepartment.setName(updatedDepartment.getName());
        currentDepartment.setLoc(updatedDepartment.getLoc());

        return departmentDAO.save(currentDepartment);        
    }
}