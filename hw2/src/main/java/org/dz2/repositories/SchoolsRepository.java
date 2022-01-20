package org.dz2.repositories;

import org.dz2.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolsRepository extends JpaRepository<School, Integer> {
}
