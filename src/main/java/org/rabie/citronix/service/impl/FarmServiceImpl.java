package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.exception.FarmNullException;
import org.rabie.citronix.repository.FarmRepository;
import org.rabie.citronix.service.FarmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("farmServiceImpl")
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    public FarmServiceImpl(FarmRepository farmRepository){
        this.farmRepository = farmRepository;
    }

    public Farm save(Farm farm) {
        if(farm==null) throw new FarmNullException("Farm is null");
        return farmRepository.save(farm);
    }

    public Page<Farm> getAll(PageRequest pageRequest){
        return farmRepository.findAll(pageRequest);
    }
}
