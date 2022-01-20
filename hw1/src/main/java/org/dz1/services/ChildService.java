package org.dz1.services;

import org.dz1.configs.HibernateConfig;
import org.dz1.entities.Child;
import org.dz1.entities.Parents;
import org.dz1.entities.School;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

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

    public void AddChild(String[] lineArgs) {
        Parents parents = new ParentsService().getById(Integer.parseInt(lineArgs[1]));
        Child child = new Child(lineArgs[0], parents, Integer.parseInt(lineArgs[2]));
        child.save();
        System.out.println("Добавлен ребёнок " + child.getName() + ", Id " + child.getId());

        if (parents.getAddress() != null) {
            List<School> suitableSchools = new SchoolService().getSchools().stream().filter(x ->
                    parents.getAddress().getDistrict().getAddresses().stream().anyMatch(y ->
                            y.getId() == x.getAddress().getId())).collect(Collectors.toList());
            System.out.println("Подходящие школы:");
            for(School suitableSchool : suitableSchools)
                System.out.println(suitableSchool.getSchoolNumber() + ", Id " + suitableSchool.getId());
        }
    }

    public void ChangeSchool(String[] lineArgs) {
        Child child = this.getById(Integer.parseInt(lineArgs[0]));
        child.setSchool(new SchoolService().getById(Integer.parseInt(lineArgs[1])));
        child.update();
        System.out.println("Школа ребёнка " + child.getName() +
                " с Id " + child.getId() + " изменена на " + child.getSchool().getSchoolNumber());
    }
}
