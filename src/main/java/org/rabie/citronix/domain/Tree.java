package org.rabie.citronix.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate datePlantation;
    @ManyToOne
    private Field field;
    @OneToMany(mappedBy = "tree")
    private List<HarvestDetail> harvestDetails;
}
