package org.dz1.services;

import org.dz1.entities.School;
import org.dz1.configs.HibernateConfig;
import org.hibernate.Session;

import java.util.List;

public class SchoolService extends MasterService {
    public void saveSchool(School school) {
        saveOrUpdate(school, false);
    }

    public List<School> getSchools() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from School", School.class).list();
        }
    }

    public School getById(int id) {
        return this.getSchools().stream().filter(x -> x.getId() == id).findFirst().get();
    }
}