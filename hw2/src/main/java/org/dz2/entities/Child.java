package org.dz2.entities;

import javax.persistence.*;

@Table(name = "children")
@Entity
public class Child {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "parentsId")
    private Parents parents;
    private String name;
    private int age;
    @ManyToOne
    @JoinColumn(name = "schoolId")
    private School school;

    public Child() {
    }

    public Child(String name, Parents parents, int age) {
        this.name = name;
        this.parents = parents;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
