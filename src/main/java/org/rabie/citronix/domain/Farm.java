package org.rabie.citronix.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity

public class Farm {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private Double area;
    private LocalDate dateCreation;
    @OneToMany(mappedBy = "farm")
    private List<Field> fields;
}
