package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Field;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.repository.FieldRepository;
import org.springframework.stereotype.Component;


@Component("fieldService")
public class FieldServiceImpl {
    private final FieldRepository fieldRepository;
    public FieldServiceImpl(FieldRepository fieldRepository){
        this.fieldRepository = fieldRepository;
    }
    public Field delete(Field field) {
        if (field==null)
            throw new FieldsNullException("fields is null");
        fieldRepository.delete(field);
        return field;
    }

}
