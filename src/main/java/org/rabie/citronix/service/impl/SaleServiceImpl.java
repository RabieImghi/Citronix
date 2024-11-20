package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Sale;
import org.rabie.citronix.repository.SaleRepository;
import org.rabie.citronix.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component("saleServiceImpl")
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale save(Sale sale) {
        if(sale == null)
            throw new RuntimeException("Sale cannot be null");
        if(saleRepository.existsByHarvestId(sale.getHarvest().getId()) && sale.getId() == null)
            throw new RuntimeException("Sale already exists for this harvest");
        return saleRepository.save(sale);
    }

    public Sale findById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    public Sale update(Sale sale) {
        return null;
    }

    public void delete(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
        saleRepository.delete(sale);
    }

    public Page<Sale> findAll(PageRequest pageRequest) {
        return saleRepository.findAll(pageRequest);
    }
}
