package com.vtinstitute.vtinstitute_restapi.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;
import com.vtinstitute.vtinstitute_restapi.model.entity.Subject;

@Repository
public interface ScoreDAO extends CrudRepository<Score, Integer> {
    @Query("""
        SELECT s FROM Score s
        WHERE s.enrollment.id = :enrollmentId
        ORDER BY s.score DESC     
    """)
    List<Score> findByEnrollmentId(Integer enrollmentId);

    @Query("""
       SELECT s FROM Score s
       WHERE s.enrollment.id = :enrollmentId
       AND s.score >= 5  
    """)
    List<Score> findPassingScoresByEnrollment(
        @Param("enrollmentId") Integer enrollmentId
    );

    @Query("""
        SELECT s FROM Score s
        WHERE s.enrollment.id = :enrollmentId
        AND s.score < 5        
    """)
    List<Score> findFiledScoresByEnrollment(
        @Param("enrollmentId") Integer enrollmentId
    );

    @Query("""
        SELECT s FROM Score s
        WHERE s.enrollment.student.id = :studentId
        AND s.score >= 5        
    """)
    List<Score> findPassingScoresByStudent(
        @Param("studentId") String studentId
    );

    @Query("""
        SELECT s FROM Score s
        WHERE s.enrollment.student.id = :studentId
        AND s.score < 5        
    """)
    List<Score> findFailedScoreByStudent(
        @Param("studentId") String studentId
    );

    @Query("""
       SELECT s FROM Score s
       WHERE s.enrollment.student.idcard = :studentId     
    """)
    List<Score> findScoresByStudent(
        @Param("studentId") String studentId
    );

    @Query("""
        SELECT s FROM Score s
        WHERE s.enrollment.student.id = :idcard
        AND s.enrollment.course.id = :coursid
        AND (s.score < 5 OR s.score IS NULL)
    """)
    List<Score> getFailedScoresByEnrollment(
       int enrollmentId
    );

    boolean existsByEnrollmentAndSubject(Enrollment enrollment, Subject subject);
}
