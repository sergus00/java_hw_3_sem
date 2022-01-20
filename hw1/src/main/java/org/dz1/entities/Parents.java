package org.dz1.entities;

import org.dz1.services.ParentsService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "parents")
@Entity
public class Parents {
    @Id
    @GeneratedValue
    private int id;
    private String mother;
    private String father;
    @OneToMany(mappedBy = "parents", cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    public Parents() {
    }

    public Parents(String mother, String father) {
        this.mother = mother;
        this.father = father;
    }

    public void save() {
        new ParentsService().saveParents(this);
    }

    public void update() {
        new ParentsService().updateParents(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}