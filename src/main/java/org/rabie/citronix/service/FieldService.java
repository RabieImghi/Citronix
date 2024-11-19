package org.rabie.citronix.service;

import org.rabie.citronix.domain.Field;
import org.rabie.citronix.exception.FieldsNullException;
import org.rabie.citronix.repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;
    public FieldService(FieldRepository fieldRepository){
        this.fieldRepository = fieldRepository;
    }

    public Field save(Field field) {
        if (field==null)
            throw new FieldsNullException("fields is null");
        return fieldRepository.save(field);
    }

    public Field update(Field field) {
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
    public List<Field> getAll(){
        return fieldRepository.findAll();
    }
}
