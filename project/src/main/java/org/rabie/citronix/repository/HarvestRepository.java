package org.rabie.citronix.repository;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.enums.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest,Long> {
     Boolean existsBySessionAndHarvestDate(Session session, LocalDate harvestDate);
     @Query(value = "SELECT " +
             "CASE " +
             "   WHEN COUNT(hd) > 0 THEN true " +
             "   ELSE false " +
             "END " +
             "FROM harvest_detail hd " +
             "JOIN harvest h ON hd.harvest_id = h.id " +
             "JOIN tree t ON hd.tree_id = t.id " +
             "WHERE t.field_id = :fieldId " +
             "   AND h.session = :harvestSeason " +
             "   AND EXTRACT(YEAR FROM h.harvest_date) = :harvestYear",
             nativeQuery = true)
     boolean existsByFieldIdAndHarvestSeasonAndHarvestYear(Long fieldId, Session harvestSeason, int harvestYear);
}
