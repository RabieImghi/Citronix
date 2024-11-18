package org.rabie.citronix.service;

import org.rabie.citronix.domain.Farm;
import org.springframework.stereotype.Service;

@Service
public interface FarmService {
    Farm save(Farm farm);
}
