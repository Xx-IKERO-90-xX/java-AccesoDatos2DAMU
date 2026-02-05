package com.vtinstitute.vtinstitute_restapi.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;

@Repository
public interface ScoreDAO extends CrudRepository<Score, Integer> {
    List<Score> findByEnrollmentId(Integer enrollmentId);
}
