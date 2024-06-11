package org.sidkr.productservice.config;

import org.sidkr.productservice.utility.ProductMapperUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ProductMapperUtility productMapperUtility() {
        return new ProductMapperUtility();
    }
}
