package com.vtinstitute.vtinstitute_restapi.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;

@Repository
public interface EnrollmentDAO extends CrudRepository<Enrollment, Integer> {

    List<Enrollment> findByStudent(Student student);
}
