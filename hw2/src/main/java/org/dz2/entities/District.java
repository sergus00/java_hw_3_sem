package org.dz2.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "districts")
@Entity
public class District {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
