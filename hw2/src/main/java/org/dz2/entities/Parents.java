package org.dz2.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "parents")
@Entity
public class Parents {
    @Id
    @GeneratedValue
    private Integer id;
    private String mother;
    private String father;
    @OneToMany(mappedBy = "parents", cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<Child>(); // TODO Может и не надо new
    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    public Parents() {
    }

    public Parents(String mother, String father) {
        this.mother = mother;
        this.father = father;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMother() {
        return mother;
    }

//    public void setMother(String mother) {
//        this.mother = mother;
//    }

    public void setMother(String mother) {
        if (!Objects.equals(mother, "")) {
            this.mother = mother;
        }
    }

    public String getFather() {
        return father;
    }

//    public void setFather(String father) {
//        this.father = father;
//    }

    public void setFather(String father) {
        if (!Objects.equals(father, "")) {
            this.father = father;
        }
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
