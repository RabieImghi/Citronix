package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.HarvestDetailsNullOrEmptyException;
import org.rabie.citronix.repository.HarvestDetailRepository;
import org.rabie.citronix.service.HarvestDetailService;
import org.rabie.citronix.service.HarvestService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("harvestDetailService")
public class HarvestDetailServiceImpl implements HarvestDetailService {
    private final HarvestDetailRepository harvestDetailRepository;
    private final HarvestService harvestService;

    public HarvestDetailServiceImpl(HarvestDetailRepository harvestDetailRepository,@Lazy HarvestService harvestService) {
        this.harvestDetailRepository = harvestDetailRepository;
        this.harvestService = harvestService;
    }

    public HarvestDetail save(HarvestDetail harvestDetail) {
        if(harvestDetail == null)
            throw new HarvestDetailsNullOrEmptyException("Harvest detail cannot be null");
        if(harvestDetail.getTree() == null)
            throw new HarvestDetailsNullOrEmptyException("Tree cannot be null");
        if (harvestDetail.getQuantityHarvested() == null)
            throw new HarvestDetailsNullOrEmptyException("Quantity harvested cannot be null");
        if(harvestDetail.getHarvest()==null)
            throw new HarvestDetailsNullOrEmptyException("Harvest cannot be null");
        return harvestDetailRepository.save(harvestDetail);
    }

    public List<HarvestDetail> getListDetail(List<Tree> trees) {
        List<HarvestDetail> harvestDetails = new ArrayList<>();
        for (Tree tree : trees) {
            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setTree(tree);
            harvestDetail.setQuantityHarvested(tree.getProductivity());
            harvestDetails.add(harvestDetail);
        }
        return harvestDetails;
    }

    public Boolean existsByHarvestAndTree(Long harvestId, Long treeId) {
        return harvestDetailRepository.existsByHarvestIdAndTreeId(harvestId, treeId);
    }

    public void saveAll(List<HarvestDetail> harvestDetails) {
        if(harvestDetails == null || harvestDetails.isEmpty())
            throw new HarvestDetailsNullOrEmptyException("Harvest details cannot be null or empty");
        harvestDetailRepository.saveAll(harvestDetails);
    }

    public void deleteByHarvestId(Long id) {
        harvestDetailRepository.deleteByHarvestId(id);
    }

    public void deleteByTreeId(Long id) {
        List<HarvestDetail> lists = harvestDetailRepository.findByTreeId(id);
        lists.forEach(harvestDetail -> {
            Harvest harvest = harvestDetail.getHarvest();
            harvest.setTotalQuantity(harvest.getTotalQuantity()-harvestDetail.getQuantityHarvested());
            harvestService.save(harvest, null);
        });
        harvestDetailRepository.deleteAll(lists);
    }
    public HarvestDetail findById(Long id) {
        return harvestDetailRepository.findById(id).orElse(null);
    }

}
