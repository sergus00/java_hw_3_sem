package org.dz2.config;

import org.dz2.entities.Address;
import org.dz2.entities.District;
import org.dz2.entities.School;
import org.dz2.repositories.AddressesRepository;
import org.dz2.repositories.DistrictsRepository;
import org.dz2.repositories.SchoolsRepository;

import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DistrictsConfig {
    public DistrictsConfig(DistrictsRepository districtsRepository, AddressesRepository addressesRepository, SchoolsRepository schoolsRepository) {
        if (districtsRepository.count() > 0) return;

        ArrayList<District> districts = new ArrayList<>();
        districts.add(new District());
        districts.add(new District());
        districts.add(new District());
        districts.add(new District());
        districtsRepository.saveAll(districts);

        int i = 0;
        List<Address> addresses = new ArrayList<>(25);
        for (String addressName : new String[]{
                "Pushkin street, Kolotushkin house", "London SW1A 0AA",
                "20 W 34th St", "Mira street, 19, Ekaterinburg",
                "8 Marta street, 13, Ekaterinburg", "Vaynera street, 4, Ekaterinburg",
                "Lenina, 33, Ekaterinburg,", "Red Square, Moscow"
        }) {
            Address address = new Address(addressName);
            addresses.add(address);
            address.setDistrict(districts.get(i % districts.size()));
            i++;
        }
        addressesRepository.saveAll(addresses);

        List<School> schools = new ArrayList<>(25);
        for (i = 0; i < 8; i++) schools.add(new School("School #" + i, addresses.get(i)));
        schoolsRepository.saveAll(schools);
    }
}
