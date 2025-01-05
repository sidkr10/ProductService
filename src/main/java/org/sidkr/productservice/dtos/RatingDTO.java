package org.sidkr.productservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private Integer count;
    private Double rate;
}
