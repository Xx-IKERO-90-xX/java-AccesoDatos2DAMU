package com.vtinstitute.vtinstitute_restapi.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vtinstitute.vtinstitute_restapi.model.entity.Student;

@Repository
public interface StudentDAO extends JpaRepository<Student, String> {
    @Query("""
        SELECT s FROM Student s
        WHERE s.idcard = :idcard
        AND s.email = :email
    """)
    List<Student> getStudentByIdcardEmail(@Param("email") String email, @Param("idcard") String idcard);

    @Query("""
        SELECT s FROM Student s
        WHERE s.email = :email
    """)
    List<Student> getStudentByEmail(@Param("email") String email);
}
