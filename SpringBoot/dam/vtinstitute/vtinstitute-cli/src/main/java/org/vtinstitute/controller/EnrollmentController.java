package org.vtinstitute.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vtinstitute.controller.*;
import org.vtinstitute.models.entity.Score;
import org.vtinstitute.models.entity.Student;
import org.vtinstitute.models.entity.Cours;
import org.vtinstitute.models.entity.Enrollment;
import org.vtinstitute.models.ApiManager;

public class EnrollmentController {
    private LogsController logsController = new LogsController();
    private ApiManager apiManager = new ApiManager();

    public void enrollStudentApi(String idcard, int idcours, int year) throws IOException {
        Student student = apiManager.getStudent(idcard);
        Cours cours = apiManager.getCours(idcours);
        Enrollment newEnrollment = new Enrollment();
        newEnrollment.setStudent(student);
        newEnrollment.setCourse(cours);
        newEnrollment.setYear(year);
        apiManager.enrollStudentApi(newEnrollment);
    }

    public List<Enrollment> getEnrollmentsByStudentCours(String idcard, int idcours) throws IOException {
        List<Enrollment> enrollments = apiManager.getEnrollmentsByStudentCours(idcard, idcours);
        return enrollments;
    }
}
