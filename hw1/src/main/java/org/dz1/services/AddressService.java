package org.dz1.services;

import org.dz1.entities.Address;
import org.dz1.configs.HibernateConfig;
import org.hibernate.Session;

import java.util.List;

public class AddressService extends MasterService {
    public void saveAddress(Address address) {
        saveOrUpdate(address, false);
    }

    public List<Address> getAddresss() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Address", Address.class).list();
        }
    }

    public Address getById(int id) {
        return this.getAddresss().stream().filter(x -> x.getId() == id).findFirst().get();
    }
}