package org.rabie.citronix.service;

import org.rabie.citronix.domain.Field;
import org.springframework.stereotype.Service;

@Service
public interface FieldService {
    Field save(Field field);
}
