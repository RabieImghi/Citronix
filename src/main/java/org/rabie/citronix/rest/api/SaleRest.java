package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.Sale;
import org.rabie.citronix.rest.mapper.SaleMapper;
import org.rabie.citronix.rest.vm.request.sale.SaleSaveRequest;
import org.rabie.citronix.rest.vm.request.sale.SaleUpdateRequest;
import org.rabie.citronix.rest.vm.response.SaleResponse;
import org.rabie.citronix.service.HarvestService;
import org.rabie.citronix.service.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/sale")
public class SaleRest {
    private final SaleService saleService;
    private final SaleMapper saleMapper;
    private final HarvestService harvestService;

    public SaleRest(SaleService saleService, SaleMapper saleMapper, HarvestService harvestService) {
        this.saleService = saleService;
        this.saleMapper = saleMapper;
        this.harvestService = harvestService;
    }

    @PostMapping("/save")
    public ResponseEntity<SaleResponse> save(@Valid @RequestBody SaleSaveRequest saleSaveRequest) {
        Harvest harvest = harvestService.findById(saleSaveRequest.getHarvestId());
        if(harvest == null)
            throw new RuntimeException("Harvest not found");
        Sale sale = new Sale();
        return saveAndUpdateSale(sale, saleSaveRequest.getSaleDate(), saleSaveRequest.getUnitPrice(), saleSaveRequest.getClient(), harvest);
    }

    @PutMapping("/update")
    public ResponseEntity<SaleResponse> update(@Valid @RequestBody SaleUpdateRequest saleUpdateRequest) {
        Harvest harvest = harvestService.findById(saleUpdateRequest.getHarvestId());
        if(harvest == null)
            throw new RuntimeException("Harvest not found");
        Sale sale = saleService.findById(saleUpdateRequest.getId());
        sale.setId(saleUpdateRequest.getId());
        return saveAndUpdateSale(sale, saleUpdateRequest.getSaleDate(), saleUpdateRequest.getUnitPrice(), saleUpdateRequest.getClient(), harvest);
    }
    public ResponseEntity<SaleResponse> saveAndUpdateSale(Sale sale, LocalDate saleDate, Double unitPrice, String client, Harvest harvest) {
        sale.setSaleDate(saleDate);
        sale.setUnitPrice(unitPrice);
        sale.setClient(client);
        sale.setHarvest(harvest);
        sale = saleService.save(sale);
        return ResponseEntity.ok(saleMapper.toSaleResponse(sale));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.ok("Sale deleted successfully");
    }

    @GetMapping("/getAll")
    public Page<SaleResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Sale> sales = saleService.findAll(pageRequest);
        return sales.map(saleMapper::toSaleResponse);
    }
}
