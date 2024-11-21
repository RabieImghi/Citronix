package org.rabie.citronix.specification;

import jakarta.persistence.criteria.Predicate;
import org.rabie.citronix.domain.Farm;
import org.rabie.citronix.dto.SearchFarmDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FarmSpecification {
    public static Specification<Farm> getUsersByCriteria(SearchFarmDto searchDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchDto.getId() != null && !searchDto.getId().toString().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("id"), searchDto.getId()));
            }

            if (searchDto.getName() != null && !searchDto.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + searchDto.getName().toLowerCase() + "%"));
            }

            if (searchDto.getLocation() != null && !searchDto.getLocation().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("location"), searchDto.getLocation()));
            }

            if (searchDto.getArea() != null && searchDto.getArea()>0) {
                predicates.add(criteriaBuilder.equal(root.get("area"), searchDto.getArea()));
            }

            if (searchDto.getDateCreation() != null) {
                predicates.add(criteriaBuilder.equal(root.get("dateCreation"), searchDto.getDateCreation()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
