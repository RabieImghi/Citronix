package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.Sale;
import org.rabie.citronix.repository.SaleRepository;
import org.rabie.citronix.service.HarvestService;
import org.rabie.citronix.service.SaleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component("saleServiceImpl")
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final HarvestService harvestService;
    public SaleServiceImpl(SaleRepository saleRepository,@Lazy HarvestService harvestService) {
        this.saleRepository = saleRepository;
        this.harvestService = harvestService;
    }

    public Sale save(Sale sale) {
        if(sale == null)
            throw new RuntimeException("Sale cannot be null");

        sale = saleRepository.save(sale);
        Harvest harvest = harvestService.findById(sale.getHarvest().getId());
        if(harvest.getTotalQuantity() < sale.getQuantity())
            throw new RuntimeException("Sale quantity cannot be greater than harvest quantity");
        harvest.setTotalQuantity(harvest.getTotalQuantity() - sale.getQuantity());
        harvestService.save(harvest, null);
        return sale;
    }

    public Sale findById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
    }


    public void delete(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
        saleRepository.delete(sale);
    }

    public Page<Sale> findAll(PageRequest pageRequest) {
        return saleRepository.findAll(pageRequest);
    }

    public void deleteByHarvestId(Long harvestId) {
        saleRepository.deleteByHarvestId(harvestId);
    }
}
