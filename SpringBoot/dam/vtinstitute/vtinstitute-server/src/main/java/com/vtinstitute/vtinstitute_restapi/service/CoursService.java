package com.vtinstitute.vtinstitute_restapi.service;

import com.vtinstitute.vtinstitute_restapi.model.entity.Cours;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vtinstitute.vtinstitute_restapi.model.dao.CoursDAO;

@Service
public class CoursService {
    @Autowired
    private CoursDAO coursDAO;

    public List<Cours> findAll() {
        return (List<Cours>) coursDAO.findAll();
    }

    public Cours findById(int id) {
        Optional<Cours> cours = coursDAO.findById(id);
        
        if (!cours.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours not found");
        }
        return cours.get();
    }

    public Cours save(Cours cours) {
        return coursDAO.save(cours);
    }

    public Cours update(int id, Cours cours) {
        if (!coursDAO.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours not found");
        }
        Cours currentCours = coursDAO.findById(id).get();
        currentCours.setName(cours.getName());
        return coursDAO.save(currentCours);
    }

    public Cours delete(int id) {
        if (!coursDAO.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cours not found");
        }
        Cours cours = coursDAO.findById(id).get();
        coursDAO.delete(cours);
        return cours;
    }
}
