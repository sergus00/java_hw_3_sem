package org.dz1.entities;

import org.dz1.services.DistrictService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "districts")
@Entity
public class District {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public void Save() {
        new DistrictService().saveDistrict(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
