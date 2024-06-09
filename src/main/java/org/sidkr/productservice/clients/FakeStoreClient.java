package org.sidkr.productservice.clients;

import org.sidkr.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Product getProduct(long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity("https://fakestoreapi.com/products/" + productId, Product.class).getBody();
    }

}
