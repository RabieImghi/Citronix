package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.exception.AreaOfFiledMustBeInfAreaOfFarmException;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.repository.FieldRepository;
import org.rabie.citronix.service.FieldService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("fieldService")
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    public FieldServiceImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field save(Field field) {
        if (field==null)
            throw new FieldsNullException("fields is null");

        return fieldRepository.save(field);
    }

    public Field delete(Field field) {
        if (field==null)
            throw new FieldsNullException("fields is null");
        fieldRepository.delete(field);
        return field;
    }
}

