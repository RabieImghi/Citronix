package org.rabie.citronix.service;

import org.rabie.citronix.domain.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FieldService {
    Field save(Field field);
    Field delete(Field field);
    Page<Field> getAll(PageRequest pageRequest);
    Field findById(Long id);
}
