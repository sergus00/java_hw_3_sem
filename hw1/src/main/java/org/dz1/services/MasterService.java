package org.dz1.services;

import org.dz1.configs.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MasterService {
    public void saveOrUpdate(Object object, boolean needToUpdate) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (needToUpdate)
                session.update(object);
            else
                session.save(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Object> get(String strClass, Class objectClass) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from " + strClass, objectClass).list();
        }
    }

//    public Object getById(int id, Object obj) {
//        return this.getSchools().stream().filter(x -> x.getId() == id).findFirst().get();
//    }
}
