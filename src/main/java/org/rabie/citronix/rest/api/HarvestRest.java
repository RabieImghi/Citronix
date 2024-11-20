package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.enums.Session;
import org.rabie.citronix.rest.mapper.HarvestMapper;
import org.rabie.citronix.rest.vm.request.Harvest.HarvestSaveRequest;
import org.rabie.citronix.rest.vm.response.HarvestResponse;
import org.rabie.citronix.service.HarvestService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        harvest.setHarvestDate(request.getHarvestDate());
        harvest.setTotalQuantity(request.getTotalQuantity());
        harvest = harvestService.save(harvest, request.getFieldId());
        HarvestResponse response = new HarvestResponse();
        response.setId(harvest.getId());
        response.setSession(String.valueOf(harvest.getSession()));
        response.setHarvestDate(harvest.getHarvestDate());
        response.setTotalQuantity(harvest.getTotalQuantity());
        return ResponseEntity.ok(response);
    }


}
