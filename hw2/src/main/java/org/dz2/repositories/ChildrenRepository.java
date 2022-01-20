package org.dz2.repositories;

import org.dz2.entities.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildrenRepository extends JpaRepository<Child, Integer> {
}
