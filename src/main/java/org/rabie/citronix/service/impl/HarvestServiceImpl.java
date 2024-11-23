package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Field;
import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.HarvestDetail;
import org.rabie.citronix.domain.Tree;
import org.rabie.citronix.domain.enums.Session;
import org.rabie.citronix.exception.HarvestNullException;
import org.rabie.citronix.repository.HarvestRepository;
import org.rabie.citronix.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Component("harvestService")
public class HarvestServiceImpl implements HarvestService {
    private final HarvestRepository harvestRepository;
    private final TreeService treeService;
    private final HarvestDetailService harvestDetailService;
    private final SaleService saleService;
    public HarvestServiceImpl(HarvestRepository harvestRepository,TreeService treeService,HarvestDetailService harvestDetailService, SaleService saleService) {
        this.harvestRepository = harvestRepository;
        this.treeService = treeService;
        this.harvestDetailService = harvestDetailService;
        this.saleService = saleService;
    }

    public Harvest save(Harvest harvest, Long fieldId) {
        if (harvest == null) throw new HarvestNullException("Harvest cannot be null");
        harvest.setSession(getSessionFromDate(harvest.getHarvestDate()));
        if(harvestRepository.existsByFieldIdAndHarvestSeasonAndHarvestYear(fieldId,harvest.getSession(), YearMonth.from(harvest.getHarvestDate()).getYear()) && harvest.getId()==null)
            throw new HarvestNullException("Harvest already exists for this session");
        if(fieldId!=null){
            return saveHarvestWithDetails(fieldId, harvest);
        }else
            return harvestRepository.save(harvest);
    }

    public Harvest saveHarvestWithDetails(Long fieldId, Harvest harvest) {
        List<Tree> trees = treeService.getByFieldId(fieldId);
        Tree lastCreatedTree = trees.stream().max((tree1, tree2) -> tree1.getDatePlantation().compareTo(tree2.getDatePlantation())).orElse(null);
        if(lastCreatedTree != null && lastCreatedTree.getDatePlantation().isAfter(harvest.getHarvestDate()))
            throw new HarvestNullException("Harvest date must be after the last tree plantation date");


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
    public Harvest findById(Long id) {
        return harvestRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        Harvest harvest = findById(id);
        if(harvest == null)
            throw new HarvestNullException("Harvest not found");
        saleService.deleteByHarvestId(id);
        harvestDetailService.deleteByHarvestId(id);
        harvestRepository.delete(harvest);
    }

    public Page<Harvest> getAll(PageRequest pageRequest) {
        return harvestRepository.findAll(pageRequest);
    }


}
