package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.enums.Session;
import org.rabie.citronix.rest.mapper.HarvestMapper;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestSaveRequest;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestUpdateRequest;
import org.rabie.citronix.rest.vm.response.HarvestResponse;
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
    public ResponseEntity<HarvestResponse> save(@Valid @RequestBody HarvestSaveRequest request) {
        Harvest harvest = new Harvest();
        return saveUpdateHarvest(harvest, request.getHarvestDate(), request.getTotalQuantity(), request.getFieldId());
    }

    @PutMapping("/update")
    public ResponseEntity<HarvestResponse> update(@Valid @RequestBody HarvestUpdateRequest harvestUpdateRequest) {
        Harvest harvest = harvestService.findById(harvestUpdateRequest.getId());
        if(harvest == null)
            throw new RuntimeException("Harvest not found");
        return saveUpdateHarvest(harvest, harvestUpdateRequest.getHarvestDate(), harvestUpdateRequest.getTotalQuantity(), harvestUpdateRequest.getFieldId());
    }

    private ResponseEntity<HarvestResponse> saveUpdateHarvest(Harvest harvest, LocalDate harvestDate, Double totalQuantity, Long fieldId) {
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
    public Page<HarvestResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return harvestService.getAll(PageRequest.of(page, size)).map(harvestMapper::toResponse);
    }
}
