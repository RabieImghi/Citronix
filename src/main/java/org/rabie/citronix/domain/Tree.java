package org.rabie.citronix.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate datePlantation;
    @ManyToOne
    private Field field;
    @OneToMany(mappedBy = "tree")
    private List<HarvestDetail> harvestDetails;

    public Long getAge() {
        return ChronoUnit.YEARS.between(datePlantation, LocalDate.now());
    }

    public Double getProductivity() {
        long age = this.getAge();
        if(age< 3 ) return 2.5;
        else if(age < 10) return 12.;
        else return 20.;
    }
}
