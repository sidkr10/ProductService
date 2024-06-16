package org.sidkr.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id", referencedColumnName = "id")
    private Rating rating;

}
