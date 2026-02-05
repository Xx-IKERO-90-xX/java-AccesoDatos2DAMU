package com.vtinstitute.vtinstitute_restapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vtinstitute.vtinstitute_restapi.model.entity.SubjectCours;

@Repository
public interface SubjectCoursDAO extends CrudRepository<SubjectCours, Integer> {}

