package org.rabie.citronix.repository;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field,Long> {
    List<Field> findByFarmId(Long id);
}