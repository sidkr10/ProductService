package org.sidkr.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rating {
    @Id
    private Long id;
    private double rate;
    private int count;
}
