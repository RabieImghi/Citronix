package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.exception.HarvestDetailsNullOrEmptyException;
import org.rabie.citronix.repository.HarvestDetailRepository;
import org.rabie.citronix.service.HarvestDetailService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("harvestDetailService")
public class HarvestDetailServiceImpl implements HarvestDetailService {
    private final HarvestDetailRepository harvestDetailRepository;

    public HarvestDetailServiceImpl(HarvestDetailRepository harvestDetailRepository) {
        this.harvestDetailRepository = harvestDetailRepository;
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

    @Override
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

    @Override
    public Boolean existsByHarvestAndTree(Long harvestId, Long treeId) {
        return harvestDetailRepository.existsByHarvestIdAndTreeId(harvestId, treeId);
    }

    public void saveAll(List<HarvestDetail> harvestDetails) {
        if(harvestDetails == null || harvestDetails.isEmpty())
            throw new HarvestDetailsNullOrEmptyException("Harvest details cannot be null or empty");
        harvestDetailRepository.saveAll(harvestDetails);
    }

    @Override
    public void deleteByHarvestId(Long id) {
        harvestDetailRepository.deleteByHarvestId(id);
    }
}
