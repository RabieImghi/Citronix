package org.rabie.citronix.repository;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.enums.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest,Long> {
     Boolean existsBySessionAndHarvestDate(Session session, LocalDate harvestDate);

}
