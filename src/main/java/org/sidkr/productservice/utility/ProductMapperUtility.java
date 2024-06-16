package org.sidkr.productservice.utility;

import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.dtos.RatingDTO;
import org.sidkr.productservice.models.Category;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.models.Rating;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperUtility {

    public ProductDTO convertProducttoProductDTO(Product product) {
        if(product == null)
            return null;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        RatingDTO ratingDTO = new RatingDTO(product.getRating().getCount(),product.getRating().getRate());
        productDTO.setRating(ratingDTO);
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setImage(product.getImageUrl());
        return productDTO;
    }

    public Product convertProductDTOToProduct(ProductDTO productDTO) {
        if(productDTO == null)
            return null;
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        Rating rating = new Rating();
        rating.setCount(productDTO.getRating().getCount());
        rating.setRate(productDTO.getRating().getRating());
        product.setRating(rating);
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDTO.getImage());
        return product;
    }
}
