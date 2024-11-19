package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.dto.SearchFarmDto;
import org.rabie.citronix.rest.mapper.FarmMapper;
import org.rabie.citronix.rest.vm.request.farm.FarmSaveRequest;
import org.rabie.citronix.rest.vm.request.farm.FarmUpdateRequest;
import org.rabie.citronix.rest.vm.response.FarmResponse;
import org.rabie.citronix.service.FarmService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<FarmResponse> save(@Valid @RequestBody FarmSaveRequest farmSaveRequest){
        Farm farm = farmMapper.toFarmFromSaveRequest(farmSaveRequest);
        farm = farmService.save(farm);
        return ResponseEntity.ok(farmMapper.toFarmResponse(farm));
    }

    @PutMapping("/update")
    public ResponseEntity<FarmResponse> update(@Valid @RequestBody FarmUpdateRequest farmUpdateRequest){
        Farm farm = farmMapper.toFarmFromUpdateRequest(farmUpdateRequest);
        farm = farmService.save(farm);
        return ResponseEntity.ok(farmMapper.toFarmResponse(farm));
    }

    @GetMapping("getAll")
    public Page<FarmResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Farm> farms = farmService.getAll(pageRequest);
        return farms.map(farmMapper::toFarmResponse);
    }


    @PostMapping("/search")
    public Page<FarmResponse> search(@RequestBody SearchFarmDto searchFarmDto, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Farm> farms = farmService.searchFarms(searchFarmDto,pageRequest);
        return farms.map(farmMapper::toFarmResponse);
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<FarmResponse> delete(@PathVariable Long id){
        Farm farm = farmService.findById(id);
        farm = farmService.delete(farm);
        return ResponseEntity.ok(farmMapper.toFarmResponse(farm));
    }
}
