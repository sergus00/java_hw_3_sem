package org.dz1.configs;

import org.dz1.models.Address;
import org.dz1.models.District;
import org.dz1.models.School;
import org.dz1.services.AddressService;

public class DistrictsConfig {
    public static void addDistricts() {
        if (new AddressService().getAddresss().size() != 0) return;

        String[] addresses = new String[]{
                "Pushkin street, Kolotushkin house", "London SW1A 0AA",
                "20 W 34th St", "Mira street, 19, Ekaterinburg",
                "8 Marta street, 13, Ekaterinburg", "Vaynera street, 4, Ekaterinburg",
                "Lenina, 33, Ekaterinburg,", "Red Square, Moscow"
        };

        int n = 0;
        for (int i = 0; i < addresses.length / 2; i++) {
            District district = new District();
            district.Save();

            for (int j = 0; j < 2; j++) {
                Address address = new Address(addresses[n]);
                address.setDistrict(district);
                address.Save();

                n++;
                School school = new School("School №" + n, address);
                school.Save();
            }
        }

        System.out.println("Добавлены районы, адреса и школы");
    }
}
