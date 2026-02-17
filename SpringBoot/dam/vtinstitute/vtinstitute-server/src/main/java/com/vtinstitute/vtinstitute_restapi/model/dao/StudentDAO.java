package com.vtinstitute.vtinstitute_restapi.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vtinstitute.vtinstitute_restapi.model.entity.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, String> {}
