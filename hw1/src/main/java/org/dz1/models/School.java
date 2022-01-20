package org.dz1.models;

import org.dz1.services.SchoolService;

import javax.persistence.*;

@Table(name = "schools")
@Entity
public class School {
    @Id
    @GeneratedValue
    private int id;
    private String schoolNumber;
    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    public School() {
    }

    public School(String schoolNumber, Address address) {
        this.schoolNumber = schoolNumber;
        this.address = address;
    }

    public void Save() {
        new SchoolService().saveSchool(this);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
