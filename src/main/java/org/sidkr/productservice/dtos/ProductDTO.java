package org.sidkr.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.sidkr.productservice.models.Rating;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private Rating rating;
}
