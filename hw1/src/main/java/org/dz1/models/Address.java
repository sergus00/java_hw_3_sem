package org.dz1.models;

import org.dz1.services.AddressService;

import javax.persistence.*;

@Table(name = "addresses")
@Entity
public class Address {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "districtId")
    private District district;
    private String address;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    public void Save() {
        new AddressService().saveAddress(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
