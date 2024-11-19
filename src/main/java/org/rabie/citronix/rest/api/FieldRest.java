package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.rest.mapper.FieldMapper;
import org.rabie.citronix.rest.vm.request.field.FieldSaveRequest;
import org.rabie.citronix.rest.vm.response.FieldResponse;
import org.rabie.citronix.service.FarmService;
import org.rabie.citronix.service.FieldService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/field")
public class FieldRest {
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;
    private final FarmService farmService;

    public FieldRest(FieldService fieldService, FieldMapper fieldMapper, FarmService farmService) {
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
        this.farmService = farmService;
    }


    @PostMapping("save")
    public FieldResponse save(@Valid @RequestBody FieldSaveRequest fieldSaveRequest){
        Field field = new Field();
        field.setName(fieldSaveRequest.getName());
        field.setArea(fieldSaveRequest.getArea());
        Farm farm = farmService.findById(fieldSaveRequest.getFarmId());
        if(farm==null) throw new RuntimeException("Farm not found");
        field.setFarm(farm);
        field = fieldService.save(field);
        return fieldMapper.toFieldResponse(field);
    }


    @PostMapping("delete")
    public FieldResponse delete(Field field){
        field = fieldService.delete(field);
        return fieldMapper.toFieldResponse(field);
    }

}
