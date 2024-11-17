package org.rabie.citronix.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double area;
    @ManyToOne
    private Farm farm;
    @OneToMany(mappedBy = "field")
    private List<Tree> trees;
}
