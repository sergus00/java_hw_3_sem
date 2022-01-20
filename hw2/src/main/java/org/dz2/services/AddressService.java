package org.dz2.services;

import org.dz2.entities.Address;
import org.dz2.repositories.AddressesRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressesRepository addressesRepository;

    public AddressService(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    public List<Address> getAllAddresses() {
        return addressesRepository.findAll();
    }

    public Address getById(Integer id) {
        return addressesRepository.findById(id).orElse(null);
    }
}
