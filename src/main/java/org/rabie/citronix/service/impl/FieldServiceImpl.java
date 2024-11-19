package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Field;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("fieldService")
public class FieldServiceImpl {
    private final FieldRepository fieldRepository;
    private final FarmServiceImpl farmService;
    public FieldServiceImpl(FieldRepository fieldRepository,@Qualifier("farmServiceImpl") FarmServiceImpl farmService) {
        this.fieldRepository = fieldRepository;
        this.farmService = farmService;
    }

    public Field delete(Field field) {
        if (field==null)
            throw new FieldsNullException("fields is null");
        fieldRepository.delete(field);
        return field;
    }
}

