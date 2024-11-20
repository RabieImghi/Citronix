package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.domain.enums.Session;
import org.rabie.citronix.exception.HarvestNullException;
import org.rabie.citronix.repository.HarvestRepository;
import org.rabie.citronix.service.HarvestDetailService;
import org.rabie.citronix.service.HarvestService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Component("harvestService")
public class HarvestServiceImpl implements HarvestService {
    private final HarvestRepository harvestRepository;
    private final TreeServiceImpl treeService;
    private final HarvestDetailServiceImpl harvestDetailService;
    public HarvestServiceImpl(HarvestRepository harvestRepository,TreeServiceImpl treeService,HarvestDetailServiceImpl harvestDetailService) {
        this.harvestRepository = harvestRepository;
        this.treeService = treeService;
        this.harvestDetailService = harvestDetailService;
    }

    public Harvest save(Harvest harvest, Long fieldId) {
        if (harvest == null) throw new HarvestNullException("Harvest cannot be null");
        harvest.setSession(getSessionFromDate(harvest.getHarvestDate()));
        if(harvestRepository.existsBySessionAndHarvestDate(harvest.getSession(), harvest.getHarvestDate()))
            throw new HarvestNullException("Harvest already exists for this session");
        if(fieldId!=null){
            return saveHarvestWithDetails(fieldId, harvest);
        }else
            return harvestRepository.save(harvest);
    }

    public Harvest saveHarvestWithDetails(Long fieldId, Harvest harvest) {
        List<Tree> trees = treeService.getByFieldId(fieldId);
        List<HarvestDetail> harvestDetails = harvestDetailService.getListDetail(trees);
        double totalQuantity = harvestDetails.stream().map(HarvestDetail::getQuantityHarvested).reduce(0., Double::sum);
        harvest.setTotalQuantity(totalQuantity);
        Harvest savedHarvest = harvestRepository.save(harvest);
        harvestDetails = harvestDetails.stream().map(harvestDetail -> {
            harvestDetail.setHarvest(savedHarvest);
            return harvestDetail;
        }).toList();
        harvestDetailService.saveAll(harvestDetails);
        return savedHarvest;
    }
    public Session getSessionFromDate(LocalDate date) {
        int month = date.getMonthValue();
        return switch (month) {
            case 1, 2, 3 -> Session.WINTER;
            case 4, 5, 6 -> Session.SPRING;
            case 7, 8, 9 -> Session.SUMMER;
            case 10, 11, 12 -> Session.AUTUMN;
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }

}
