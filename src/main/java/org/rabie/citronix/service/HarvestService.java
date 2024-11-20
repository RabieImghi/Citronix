package org.rabie.citronix.service;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.enums.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface HarvestService {
    Harvest save(Harvest harvest,Long fieldId);
    Session getSessionFromDate(LocalDate date);
    Harvest findById(Long id);
    void delete(Long id);
    Page<Harvest> getAll(PageRequest pageRequest);

}
