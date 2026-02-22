package com.vtinstitute.vtinstitute_restapi.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import com.vtinstitute.vtinstitute_restapi.model.entity.Enrollment;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;
import com.vtinstitute.vtinstitute_restapi.model.entity.Student;

@Repository
public interface EnrollmentDAO extends CrudRepository<Enrollment, Integer> {

    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByCourseId(Integer courseId);

    boolean existsByStudentAndCourse(Student student, Cours cours);

    long countByStudentAndCourse(Student student, Cours cours);

    @Query("""
        SELECT e FROM Enrollment e
        WHERE e.course.id = :course_id
        AND e.student.idcard = :idcard     
    """)
    List<Enrollment> findByStudentAndCourse(int course_id, String idcard);

    @Query("""
        SELECT COUNT(e) FROM Enrollment e WHERE e.student.idcard = :idcard     
    """)
    int getCountEnrollmentStudent(String idcard);

    @Query("""
       SELECT e FROM enrollment e
       WHERE e.student.idcard = :idcard
       ORDER BY e.year DESC     
    """)
    List<Enrollment> getLastStudentEnrollment(String idcard);

    
}
