package org.dz2.services;

import org.dz2.entities.Child;
import org.dz2.entities.Parents;
import org.dz2.entities.School;
import org.dz2.repositories.ChildrenRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildService {
    private final ChildrenRepository childrenRepository;
    private final ParentsService parentsService;
    private final SchoolService schoolService;

    public ChildService(ChildrenRepository childrenRepository, ParentsService parentsService, SchoolService schoolService) {
        this.childrenRepository = childrenRepository;
        this.parentsService = parentsService;
        this.schoolService = schoolService;
    }

    public Child addChild(Child child, Integer parentsId) {
        Parents parents = parentsService.GetById(parentsId);
        if (parents == null) {
            return null;
        }
        child.setParents(parents);
        childrenRepository.save(child);
        return child;
    }

    public List<Child> getAllChildren() {
        return childrenRepository.findAll();
    }

    public Child getById(Integer id) {
        return childrenRepository.findById(id).orElse(null);
    }

    public List<School> getSchool(Child child) {
        Parents parents = child.getParents();
        if (parents.getAddress() != null) {
            List<School> schools = schoolService.getAllSchools();
            return schools.stream().filter(x ->
                    parents.getAddress().getDistrict().getAddresses().stream().anyMatch(y ->
                            y.getId().equals(x.getAddress().getId()))).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public boolean setSchool(Integer childId, Integer schoolId) {
        Child child = this.getById(childId);
        if (child == null) {
            return false;
        }
        if (schoolId == -1) {
            child.setSchool(null);
        } else {
            School school = schoolService.getById(schoolId);
            if (school == null) {
                return false;
            }
            child.setSchool(school);
        }
        childrenRepository.save(child);
        return true;
    }
}
