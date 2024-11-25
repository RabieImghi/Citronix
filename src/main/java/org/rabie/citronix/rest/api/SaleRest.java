package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.Sale;
import org.rabie.citronix.rest.mapper.SaleMapper;
import org.rabie.citronix.rest.vm.request.sale.SaleSaveRequestVM;
import org.rabie.citronix.rest.vm.request.sale.SaleUpdateRequestVM;
import org.rabie.citronix.rest.vm.response.SaleResponseVM;
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
    public ResponseEntity<SaleResponseVM> save(@Valid @RequestBody SaleSaveRequestVM saleSaveRequestVM) {
        Harvest harvest = harvestService.findById(saleSaveRequestVM.getHarvestId());
        if(harvest == null)
            throw new RuntimeException("Harvest not found");
        Sale sale = new Sale();
        return saveAndUpdateSale(sale, saleSaveRequestVM.getSaleDate(), saleSaveRequestVM.getUnitPrice(), saleSaveRequestVM.getClient(), harvest,saleSaveRequestVM.getQuantity());
    }

    @PutMapping("/update")
    public ResponseEntity<SaleResponseVM> update(@Valid @RequestBody SaleUpdateRequestVM saleUpdateRequestVM) {
        Harvest harvest = harvestService.findById(saleUpdateRequestVM.getHarvestId());
        if(harvest == null)
            throw new RuntimeException("Harvest not found");
        Sale sale = saleService.findById(saleUpdateRequestVM.getId());
        sale.setId(saleUpdateRequestVM.getId());
        return saveAndUpdateSale(sale, saleUpdateRequestVM.getSaleDate(), saleUpdateRequestVM.getUnitPrice(), saleUpdateRequestVM.getClient(), harvest,saleUpdateRequestVM.getQuantity());
    }
    public ResponseEntity<SaleResponseVM> saveAndUpdateSale(Sale sale, LocalDate saleDate, Double unitPrice, String client, Harvest harvest,Double quantity) {
        sale.setSaleDate(saleDate);
        sale.setUnitPrice(unitPrice);
        sale.setClient(client);
        sale.setHarvest(harvest);
        sale.setQuantity(quantity);
        sale = saleService.save(sale);
        return ResponseEntity.ok(saleMapper.toSaleResponse(sale));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.ok("Sale deleted successfully");
    }

    @GetMapping("/getAll")
    public Page<SaleResponseVM> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Sale> sales = saleService.findAll(pageRequest);
        return sales.map(saleMapper::toSaleResponse);
    }

    @GetMapping("income-calculation/{id}")
    public Double calculateIncome(@PathVariable Long id) {
        Sale sale = saleService.findById(id);
        if(sale == null)
            throw new RuntimeException("Sale not found");
        return sale.getUnitPrice() * sale.getHarvest().getTotalQuantity();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<SaleResponseVM> get(@PathVariable Long id) {
        Sale sale = saleService.findById(id);
        return ResponseEntity.ok(saleMapper.toSaleResponse(sale));
    }
}
