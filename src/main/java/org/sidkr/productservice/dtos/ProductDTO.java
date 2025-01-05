package org.sidkr.productservice.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private RatingDTO rating;
}
