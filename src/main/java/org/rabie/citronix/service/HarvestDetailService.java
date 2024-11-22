package org.rabie.citronix.service;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HarvestDetailService {
    List<HarvestDetail> getListDetail(List<Tree> trees);
    Boolean existsByHarvestAndTree(Long harvestId, Long treeId);
    HarvestDetail save(HarvestDetail harvestDetail);
    void saveAll(List<HarvestDetail> harvestDetails);
    void deleteByHarvestId(Long id);
    void deleteByTreeId(Long id);
    HarvestDetail findById(Long id);
}
