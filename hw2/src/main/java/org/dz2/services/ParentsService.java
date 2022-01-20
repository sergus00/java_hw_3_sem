package org.dz2.services;

import org.dz2.entities.Address;
import org.dz2.entities.Parents;
import org.dz2.repositories.ParentsRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentsService {
    private final ParentsRepository parentsRepository;
    private final AddressService addressService;

    public ParentsService(ParentsRepository parentsRepository, AddressService addressService) {
        this.parentsRepository = parentsRepository;
        this.addressService = addressService;
    }

    public Parents AddOrUpdateParents(Parents parents) {
        parentsRepository.save(parents);
        return parents;
    }

    public Parents addOrUpdateParents(Parents parents, Integer addressId) {
        if (addressId != -1) {
            Address address = addressService.getById(addressId);
            if (address != null) {
                parents.setAddress(address);
            }
        }
        parentsRepository.save(parents);
        return parents;
    }

    public List<Parents> GetAllParents() {
        return parentsRepository.findAll();
    }

    public Parents GetById(Integer id) {
        return parentsRepository.getById(id);
    }
}
