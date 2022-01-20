package org.dz1.services;

import org.apache.commons.cli.CommandLine;
import org.dz1.configs.HibernateConfig;
import org.dz1.entities.Parents;
import org.hibernate.Session;

import java.util.List;

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
//        return getListObjects();
//    }

    public Parents getById(int id) {
        return this.getParents().stream().filter(x -> x.getId() == id).findFirst().get(); // Какашка
    }

    public void AddParents(CommandLine lineArgs) {
        Parents parents = new Parents();
        if (lineArgs.hasOption('m')) parents.setMother(lineArgs.getOptionValue('m'));
        if (lineArgs.hasOption('f')) parents.setFather(lineArgs.getOptionValue('f'));
        parents.save();
        System.out.println("Добавлены родители " + parents.getMother() + " " + parents.getFather() +
                ", Id " + parents.getId());
    }

    public void ChangeAddress(String[] lineArgs) {
        Parents parents = this.getById(Integer.parseInt(lineArgs[0]));
        parents.setAddress(new AddressService().getById(Integer.parseInt(lineArgs[1])));
        parents.update();
        System.out.println("Адрес родителей " + parents.getFather() + " и " + parents.getMother() +
                " с Id " + parents.getId() + " изменён на " + parents.getAddress().getAddress());
    }
}