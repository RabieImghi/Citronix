package org.rabie.citronix.rest.api;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.rest.mapper.FarmMapper;
import org.rabie.citronix.rest.vm.request.FarmSaveRequest;
import org.rabie.citronix.rest.vm.response.FarmResponse;
import org.rabie.citronix.service.FarmService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/farm")
public class FarmRest {

    private final FarmService farmService;
    private final FarmMapper farmMapper;

    public FarmRest(@Qualifier("farmServiceImpl") FarmService farmService, FarmMapper farmMapper){
        this.farmService = farmService;
        this.farmMapper = farmMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<FarmResponse> save(@Validated @RequestBody FarmSaveRequest farmSaveRequest){
        Farm farm = farmMapper.toFarm(farmSaveRequest);
        farm = farmService.save(farm);
        return ResponseEntity.ok(farmMapper.toFarmResponse(farm));
    }
}
