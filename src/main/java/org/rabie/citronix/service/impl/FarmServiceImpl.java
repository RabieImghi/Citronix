package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.domain.Field;
import org.rabie.citronix.dto.SearchFarmDto;
import org.rabie.citronix.exception.AreaOfFiledMustBeInfAreaOfFarmException;
import org.rabie.citronix.exception.FarmMustBeHaveFieldsException;
import org.rabie.citronix.exception.FarmNullException;
import org.rabie.citronix.repository.FarmRepository;
import org.rabie.citronix.service.FarmService;
import org.rabie.citronix.service.FieldService;
import org.rabie.citronix.specification.FarmSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("farmServiceImpl")
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final FieldService fieldService;
    public FarmServiceImpl(FarmRepository farmRepository, FieldService fieldService){
        this.farmRepository = farmRepository;
        this.fieldService = fieldService;
    }

    public Farm save(Farm farm) {
        if(farm==null) throw new FarmNullException("Farm is null");
        if(farm.getFields() == null || farm.getFields().isEmpty())
            throw new FarmMustBeHaveFieldsException("Farm must be have Fields");
        double area = farm.getFields().stream().mapToDouble(Field::getArea).sum();
        if(area>= farm.getArea()) throw new AreaOfFiledMustBeInfAreaOfFarmException("Area of filed must be inf area of farm");
        farm = farmRepository.save(farm);
        Farm finalFarm = farm;
        farm.getFields().forEach(field ->{
            field.setFarm(finalFarm);
            fieldService.save(field);
        });
        return farm;
    }

    public Page<Farm> getAll(PageRequest pageRequest){
        return farmRepository.findAll(pageRequest);
    }

    public Page<Farm> searchFarms(SearchFarmDto searchDto, PageRequest pageRequest) {
        return farmRepository.findAll(FarmSpecification.getUsersByCriteria(searchDto), pageRequest);
    }

    public List<Farm> farmAreaGetAll(){
        List<Farm> farms = farmRepository.findAll();
        return farms.stream()
                .filter(farm -> farm.getFields().stream().mapToDouble(Field::getArea).sum() < 500)
                .collect(Collectors.toList());
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
