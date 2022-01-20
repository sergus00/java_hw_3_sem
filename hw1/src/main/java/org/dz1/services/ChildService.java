package org.dz1.services;

import org.dz1.models.Child;
import org.dz1.configs.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ChildService extends MasterService {
    public void saveChild(Child child) {
        saveOrUpdate(child, false);
    }

    public void updateChild(Child child) {
        saveOrUpdate(child, true);
    }

    public List<Child> getChildren() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Child", Child.class).list();
        }
    }

    public Child getById(int id) {
        return this.getChildren().stream().filter(x -> x.getId() == id).findFirst().get();
    }
}
