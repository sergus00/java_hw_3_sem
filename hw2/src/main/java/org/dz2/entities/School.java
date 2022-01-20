package org.dz2.entities;

import javax.persistence.*;

@Table(name = "schools")
@Entity
public class School {
    @Id
    @GeneratedValue
    private Integer id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String number) {
        this.schoolNumber = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
