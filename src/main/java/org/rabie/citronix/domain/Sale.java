package org.rabie.citronix.domain;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate saleDate;
    private Double unitPrice;
    private String client;
    @ManyToOne
    private Harvest harvest;
}
