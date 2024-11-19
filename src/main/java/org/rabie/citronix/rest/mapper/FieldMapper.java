package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.rest.vm.request.field.FieldUpdateRequest;
import org.rabie.citronix.rest.vm.response.FieldResponse;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    FieldResponse toFieldResponse(Field field);
    Field toFieldFromFieldUpdateRequest(FieldUpdateRequest fieldUpdateRequest);
}