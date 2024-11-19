package org.rabie.citronix.rest.api;

import jakarta.validation.Valid;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.rest.mapper.FieldMapper;
import org.rabie.citronix.rest.vm.request.field.FieldSaveRequest;
import org.rabie.citronix.rest.vm.request.field.FieldUpdateRequest;
import org.rabie.citronix.rest.vm.response.FieldResponse;
import org.rabie.citronix.service.FarmService;
import org.rabie.citronix.service.FieldService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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
        return saveAndUpdateField(field, fieldSaveRequest.getName(), fieldSaveRequest.getArea(), fieldSaveRequest.getFarmId());
    }


    @DeleteMapping("delete/{id}")
    public FieldResponse delete(@PathVariable Long id){
        Field field = fieldService.findById(id);
        if (field==null) throw new FieldsNullException("Field not found");
        field = fieldService.delete(field);
        return fieldMapper.toFieldResponse(field);
    }

    @PutMapping("update")
    public FieldResponse update(@Valid @RequestBody FieldUpdateRequest fieldUpdateRequest){
        Field field = new Field();
        field.setId(fieldUpdateRequest.getId());
        return saveAndUpdateField(field, fieldUpdateRequest.getName(), fieldUpdateRequest.getArea(), fieldUpdateRequest.getFarmId());
    }

    private FieldResponse saveAndUpdateField(Field field, String name, Double area, Long farmId) {
        field.setName(name);
        field.setArea(area);
        Farm farm = farmService.findById(farmId);
        if(farm==null) throw new RuntimeException("Farm not found");
        field.setFarm(farm);
        field = fieldService.save(field);
        return fieldMapper.toFieldResponse(field);
    }

    @GetMapping("getAll")
    public Page<FieldResponse> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size ){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Field> fields = fieldService.getAll(pageRequest);
        return fields.map(fieldMapper::toFieldResponse);
    }



}
