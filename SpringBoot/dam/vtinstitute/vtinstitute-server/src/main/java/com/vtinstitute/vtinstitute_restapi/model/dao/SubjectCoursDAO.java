package com.vtinstitute.vtinstitute_restapi.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vtinstitute.vtinstitute_restapi.model.entity.SubjectCours;

@Repository
public interface SubjectCoursDAO extends CrudRepository<SubjectCours, Integer> {
    @Query("""
        SELECT s FROM SubjectCours s
        WHERE s.subject.year = 1
        AND s.course.id = :coursId
    """)
    List<SubjectCours> findFirstYearByCours (
        @Param("coursId") Integer coursId
    );

    @Query("""
        SELECT s FROM SubjectCours s
        WHERE s.subject.year = 2
        AND s.course.id = :coursId        
    """)
    List<SubjectCours> findSecondYearByCours(
        @Param("coursId") Integer coursId
    );
}

