package org.rabie.citronix.rest.mapper;

import org.mapstruct.Mapper;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.rest.vm.request.field.FieldUpdateRequestVM;
import org.rabie.citronix.rest.vm.response.FieldResponseVM;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    FieldResponseVM toFieldResponse(Field field);
    Field toFieldFromFieldUpdateRequest(FieldUpdateRequestVM fieldUpdateRequestVM);
}