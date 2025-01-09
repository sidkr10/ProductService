package org.sidkr.productservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.dtos.RatingDTO;
import org.sidkr.productservice.models.Category;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.models.Rating;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // ProductDTO to Product mapping
        modelMapper.addMappings(new PropertyMap<ProductDTO, Product>() {
            @Override
            protected void configure() {
                map().setImageUrl(source.getImage());
                Category category = new Category();
                map().setCategory(category);
                map().getCategory().setName(source.getCategory());
            }
        });

        // Product to ProductDTO mapping
        modelMapper.addMappings(new PropertyMap<Product, ProductDTO>() {
            @Override
            protected void configure() {
                map().setImage(source.getImageUrl());
                map().setCategory(source.getCategory().getName());
            }
        });

        // Rating to RatingDTO mapping
        modelMapper.addMappings(new PropertyMap<RatingDTO, Rating>() {
            @Override
            protected void configure() {
                map().setRate(source.getRate());
                map().setCount(source.getCount());
            }
        });

        // Rating to RatingDTO mapping
        modelMapper.addMappings(new PropertyMap<Rating, RatingDTO>() {
            @Override
            protected void configure() {
                map().setRate(source.getRate());
                map().setCount(source.getCount());
            }
        });

        return modelMapper;
    }
}
