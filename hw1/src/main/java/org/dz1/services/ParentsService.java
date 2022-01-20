package org.dz1.services;

import java.util.List;

import org.dz1.models.Parents;
import org.dz1.configs.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParentsService extends MasterService {
    public void saveParents(Parents parents) {
        saveOrUpdate(parents, false);
    }

    public void updateParents(Parents parents) {
        saveOrUpdate(parents, true);
    }

    public List<Parents> getParents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Parents", Parents.class).list();
        }
    }

//    public List<Parents> getParents() {
//        return get("Parents", Parents.class);
//    }

    public Parents getById(int id) {
        return this.getParents().stream().filter(x -> x.getId() == id).findFirst().get();
    }
}