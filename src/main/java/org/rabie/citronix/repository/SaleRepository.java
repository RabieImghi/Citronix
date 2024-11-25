package org.rabie.citronix.repository;

import org.rabie.citronix.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
    List<Sale> findByHarvestId(Long harvestId);
    void deleteByHarvestId(Long harvestId);
}
