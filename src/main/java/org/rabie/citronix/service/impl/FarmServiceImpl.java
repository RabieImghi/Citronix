package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.dto.SearchFarmDto;
import org.rabie.citronix.exception.AreaOfFiledMustBeInfAreaOfFarmException;
import org.rabie.citronix.exception.FarmMustNotBeHaveFieldsException;
import org.rabie.citronix.exception.FarmNullException;
import org.rabie.citronix.repository.FarmRepository;
import org.rabie.citronix.service.FarmService;
import org.rabie.citronix.service.FieldService;
import org.rabie.citronix.specification.FarmSpecification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.rabie.citronix.domain.Field;


import java.util.List;

@Component("farmServiceImpl")
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final FieldService fieldService;
    public FarmServiceImpl(FarmRepository farmRepository,@Qualifier("fieldService") FieldService fieldService) {
        this.farmRepository = farmRepository;
        this.fieldService = fieldService;
    }

    public Farm save(Farm farm) {
        if(farm==null) throw new FarmNullException("Farm is null");
        return farmRepository.save(farm);
    }

    public Page<Farm> getAll(PageRequest pageRequest){
        return farmRepository.findAll(pageRequest);
    }

    public Page<Farm> searchFarms(SearchFarmDto searchDto, PageRequest pageRequest) {
        return farmRepository.findAll(FarmSpecification.getUsersByCriteria(searchDto), pageRequest);
    }
    public Farm delete(Farm farm) {
        if(farm==null) throw new FarmNullException("Farm is null");
        if(farm.getFields() != null && !farm.getFields().isEmpty())
            farm.getFields().forEach(fieldService::delete);
        farmRepository.delete(farm);
        return farm;
    }
    public Farm findById(Long id) {
        return farmRepository.findById(id).orElse(null);
    }
}
