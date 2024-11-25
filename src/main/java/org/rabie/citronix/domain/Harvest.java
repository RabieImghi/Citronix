package org.rabie.citronix.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rabie.citronix.domain.enums.Session;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Session session;
    private LocalDate harvestDate;
    private Double totalQuantity;
    @OneToMany(mappedBy = "harvest")
    private List<HarvestDetail> harvestDetails;
    @OneToMany(mappedBy = "harvest")
    private List<Sale> sales;
}
