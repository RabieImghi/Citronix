package org.rabie.citronix.repository;

import org.rabie.citronix.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    Boolean existsByHarvestId(Long harvestId);
    void deleteByHarvestId(Long harvestId);
}
