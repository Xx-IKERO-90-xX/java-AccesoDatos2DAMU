package com.vtinstitute.vtinstitute_restapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;


import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;


@Repository
public interface CoursDAO extends CrudRepository<Cours, Integer> {}