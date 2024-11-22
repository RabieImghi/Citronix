package org.rabie.citronix.repository;

import org.rabie.citronix.domain.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail,Long> {
    Boolean existsByHarvestIdAndTreeId(Long harvestId, Long treeId);
    void deleteByHarvestId(Long harvestId);
    void deleteByTreeId(Long treeId);
    List<HarvestDetail> findByTreeId(Long treeId);
}
