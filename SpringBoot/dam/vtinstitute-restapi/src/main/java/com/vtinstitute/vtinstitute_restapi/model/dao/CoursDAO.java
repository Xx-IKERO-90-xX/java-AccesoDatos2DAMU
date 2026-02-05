package com.vtinstitute.vtinstitute_restapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursDAO extends CrudRepository<Cours, Integer> {

}