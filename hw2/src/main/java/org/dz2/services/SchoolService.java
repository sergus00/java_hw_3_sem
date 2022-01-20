package org.dz2.services;

import org.dz2.entities.School;
import org.dz2.repositories.SchoolsRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolsRepository schoolsRepository;

    public SchoolService(SchoolsRepository schoolsRepository) {
        this.schoolsRepository = schoolsRepository;
    }

    public List<School> getAllSchools() {
        return schoolsRepository.findAll();
    }

    public School getById(Integer id) {
        return schoolsRepository.findById(id).orElse(null);
    }
}
