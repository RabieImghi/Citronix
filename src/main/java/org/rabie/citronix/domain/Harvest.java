package org.rabie.citronix.domain;

import jakarta.persistence.*;
import org.rabie.citronix.domain.enums.Session;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Session session;
    private LocalDate harvestDate;
    private Double totalQuantity;
    @OneToMany(mappedBy = "harvest")
    private List<HarvestDetail> harvestDetails;
    @OneToMany(mappedBy = "harvest")
    private List<Sale> sales;
}
