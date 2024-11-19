package org.rabie.citronix.rest.api;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.rest.mapper.FieldMapper;
import org.rabie.citronix.rest.vm.request.fields.FieldSaveRequest;
import org.rabie.citronix.rest.vm.response.FieldResponse;
import org.rabie.citronix.service.FieldService;
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

    public FieldRest(FieldService fieldService, FieldMapper fieldMapper){
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
    }


    @PostMapping("delete")
    public FieldResponse delete(Field field){
        field = fieldService.delete(field);
        return fieldMapper.toFieldResponse(field);
    }

}
