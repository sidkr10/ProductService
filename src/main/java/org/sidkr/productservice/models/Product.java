package org.sidkr.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sidkr.productservice.dtos.ProductDTO;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
    private Rating rating;

}
