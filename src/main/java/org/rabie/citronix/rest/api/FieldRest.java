package org.rabie.citronix.rest.api;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.rest.mapper.FieldMapper;
import org.rabie.citronix.rest.vm.request.fields.FieldSaveRequest;
import org.rabie.citronix.rest.vm.response.FieldResponse;
import org.rabie.citronix.service.FarmService;
import org.rabie.citronix.service.FieldService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/field")
public class FieldRest {
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;
    private final FarmService farmService;

    public FieldRest(FieldService fieldService, FieldMapper fieldMapper,@Qualifier("farmServiceImpl") FarmService farmService) {
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
        this.farmService = farmService;
    }

    @GetMapping("getAll")
    public List<FieldResponse> getAll(){
        List<Field> fields = fieldService.getAll();
        return fields.stream().map(fieldMapper::toFieldResponse).collect(Collectors.toList());
    }

    @PostMapping("delete")
    public FieldResponse delete(Field field){
        field = fieldService.delete(field);
        return fieldMapper.toFieldResponse(field);
    }

    @PostMapping("save")
    public FieldResponse save(FieldSaveRequest fieldSaveRequest){
        Field field = new Field();
        field.setName(fieldSaveRequest.getName());
        field.setArea(fieldSaveRequest.getArea());
        Farm farm = farmService.findById(fieldSaveRequest.getFarmId());
        if(farm==null) throw new RuntimeException("Farm not found");
        field.setFarm(farm);
        return fieldMapper.toFieldResponse(field);
    }
}
