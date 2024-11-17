package org.rabie.citronix.domain;

import jakarta.persistence.*;

@Entity
public class HarvestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double quantityHarvested;
    @ManyToOne
    private Tree tree;
    @ManyToOne
    private Harvest harvest;
}
