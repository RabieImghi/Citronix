package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.exception.AreaOfFiledMustBeInfAreaOfFarmException;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.repository.FieldRepository;
import org.rabie.citronix.service.FieldService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("fieldService")
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final TreeServiceImpl treeService;

    public FieldServiceImpl(FieldRepository fieldRepository, TreeServiceImpl treeService) {
        this.fieldRepository = fieldRepository;
        this.treeService = treeService;
    }

    public Field save(Field field) {
        if (field==null)
            throw new FieldsNullException("fields is null");
        if(field.getArea()<0.1)
            throw new AreaOfFiledMustBeInfAreaOfFarmException("Area of filed must be inf 0.1");
        if(field.getFarm().getArea()/2 < field.getArea())
            throw new AreaOfFiledMustBeInfAreaOfFarmException("Area of filed must be inf 50% of area of farm");
        if(field.getFarm().getFields().size()>=10)
            throw new AreaOfFiledMustBeInfAreaOfFarmException("Number of fields must be inf 10 fields. You have already"+ field.getFarm().getFields().size() +" fields");
        List<Field> fields = fieldRepository.findByFarmId(field.getFarm().getId());
        double area = fields.stream().mapToDouble(Field::getArea).sum() + field.getArea();
        if(area>= field.getFarm().getArea())
            throw new AreaOfFiledMustBeInfAreaOfFarmException("Area of filed must be inf area of farm");
        return fieldRepository.save(field);
    }

    public Field delete(Field field) {
        if (field==null)
            throw new FieldsNullException("fields is null");
        treeService.deleteByFieldId(field.getId());
        fieldRepository.delete(field);
        return field;
    }


    public Page<Field> getAll(PageRequest pageRequest) {
        return fieldRepository.findAll(pageRequest);
    }

    public Field findById(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }
}

