package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.rest.mapper.HarvestMapper;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestSaveRequestVM;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestUpdateRequestVM;
import org.rabie.citronix.rest.vm.response.HarvestResponseVM;
import org.rabie.citronix.service.HarvestService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/harvest")
public class HarvestRest {
    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;
    public HarvestRest(@Qualifier("harvestService") HarvestService harvestService, HarvestMapper harvestMapper) {
        this.harvestService = harvestService;
        this.harvestMapper = harvestMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<HarvestResponseVM> save(@Valid @RequestBody HarvestSaveRequestVM request) {
        Harvest harvest = new Harvest();
        return saveUpdateHarvest(harvest, request.getHarvestDate(), request.getTotalQuantity(), request.getFieldId());
    }

    @PutMapping("/update")
    public ResponseEntity<HarvestResponseVM> update(@Valid @RequestBody HarvestUpdateRequestVM harvestUpdateRequestVM) {
        Harvest harvest = harvestService.findById(harvestUpdateRequestVM.getId());
        if(harvest == null)
            throw new RuntimeException("Harvest not found");
        return saveUpdateHarvest(harvest, harvestUpdateRequestVM.getHarvestDate(), harvestUpdateRequestVM.getTotalQuantity(), harvestUpdateRequestVM.getFieldId());
    }

    private ResponseEntity<HarvestResponseVM> saveUpdateHarvest(Harvest harvest, LocalDate harvestDate, Double totalQuantity, Long fieldId) {
        harvest.setHarvestDate(harvestDate);
        harvest.setTotalQuantity(totalQuantity);
        harvest = harvestService.save(harvest, fieldId);
        return ResponseEntity.ok(harvestMapper.toResponse(harvest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        harvestService.delete(id);
        return ResponseEntity.ok("Harvest deleted successfully");
    }

    @GetMapping("/getAll")
    public Page<HarvestResponseVM> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return harvestService.getAll(PageRequest.of(page, size)).map(harvestMapper::toResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HarvestResponseVM> get(@PathVariable Long id) {
        Harvest harvest = harvestService.findById(id);
        return ResponseEntity.ok(harvestMapper.toResponse(harvest));
    }
}
