package org.sidkr.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String category;
    private Rating rating;
}
