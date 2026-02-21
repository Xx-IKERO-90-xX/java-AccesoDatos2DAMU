package com.vtinstitute.vtinstitute_restapi.service;

import com.vtinstitute.vtinstitute_restapi.model.entity.Subject;
import com.vtinstitute.vtinstitute_restapi.model.entity.SubjectCours;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.vtinstitute.vtinstitute_restapi.model.dao.EnrollmentDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.ScoreDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.StudentDAO;
import com.vtinstitute.vtinstitute_restapi.model.dao.SubjectCoursDAO;
import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import com.vtinstitute.vtinstitute_restapi.model.entity.Score;

@Service
public class SubjectService {
    @Autowired
    private SubjectCoursDAO subjectCoursDAO;

    public List<SubjectCours> getFirstYearSubjectByCours(Cours cours) {
        List<SubjectCours> subjects = subjectCoursDAO.findFirstYearByCours(cours.getId());
        return subjects;
    }

    public List<SubjectCours> getSecondYearSubjectByCours(Cours cours) {
        List<SubjectCours> subjects = subjectCoursDAO.findSecondYearByCours(cours.getId());
        return subjects;
    }
}
