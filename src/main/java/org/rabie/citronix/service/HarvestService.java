package org.rabie.citronix.service;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.enums.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface HarvestService {
    Harvest save(Harvest harvest,Long fieldId);
    Session getSessionFromDate(LocalDate date);

}
