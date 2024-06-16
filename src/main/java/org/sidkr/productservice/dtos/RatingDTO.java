package org.sidkr.productservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RatingDTO {
    private Integer count;
    private Double rate;
}
