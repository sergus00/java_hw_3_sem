package org.dz2.repositories;

import org.dz2.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressesRepository extends JpaRepository<Address, Integer> {
}
