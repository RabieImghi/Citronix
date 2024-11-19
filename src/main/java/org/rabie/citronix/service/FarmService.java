package org.rabie.citronix.service;

import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.dto.SearchFarmDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FarmService {
    Farm save(Farm farm);
    Page<Farm> getAll(PageRequest pageRequest);
    Page<Farm> searchFarms(SearchFarmDto searchDto, PageRequest pageRequest);
    Farm delete(Farm farm);
    Farm findById(Long id);
    List<Farm> farmAreaGetAll();
}
