package org.rabie.citronix.service.impl;

import org.rabie.citronix.domain.Harvest;
import org.rabie.citronix.domain.enums.Session;
import org.rabie.citronix.exception.HarvestNullException;
import org.rabie.citronix.repository.HarvestRepository;
import org.rabie.citronix.service.HarvestService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;

@Component("harvestService")
public class HarvestServiceImpl implements HarvestService {
    private final HarvestRepository harvestRepository;
    public HarvestServiceImpl(HarvestRepository harvestRepository) {
        this.harvestRepository = harvestRepository;
    }

    public Harvest save(Harvest harvest) {
        if (harvest == null) throw new HarvestNullException("Harvest cannot be null");
        harvest.setSession(getSessionFromDate(harvest.getHarvestDate()));
        if(harvestRepository.existsBySessionAndHarvestDate(harvest.getSession(), harvest.getHarvestDate())) {
            throw new HarvestNullException("Harvest already exists for this session");
        }
        else return harvestRepository.save(harvest);
    }

    public Session getSessionFromDate(LocalDate date) {
        int month = date.getMonthValue();
        return switch (month) {
            case 1, 2, 3 -> Session.WINTER;
            case 4, 5, 6 -> Session.SPRING;
            case 7, 8, 9 -> Session.SUMMER;
            case 10, 11, 12 -> Session.AUTUMN;
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }
}
