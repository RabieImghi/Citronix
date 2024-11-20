package org.rabie.citronix.service;

import org.rabie.citronix.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface SaleService {
    Sale save(Sale sale);
    Sale findById(Long id);
    Sale update(Sale sale);
    void delete(Long id);
    Page<Sale> findAll(PageRequest pageRequest);
    void deleteByHarvestId(Long harvestId);

}
