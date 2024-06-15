package org.sidkr.productservice.clients;

import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.utility.ProductMapperUtility;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {

    private final RestTemplate restTemplate;
    private final ProductMapperUtility productMapper;

    public FakeStoreClient(RestTemplate restTemplate, ProductMapperUtility productMapper) {
        this.restTemplate = restTemplate;
        this.productMapper = productMapper;
    }

    public Optional<Product> getProduct(long productId) {
        ProductDTO productDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDTO.class, productId).getBody();
        return Optional.ofNullable(productMapper.convertProductDTOToProduct(productDTO));
    }

    public List<Product> getAllProducts(){
        ProductDTO[] productDTOs = restTemplate.getForEntity("https://fakestoreapi.com/products" , ProductDTO[].class).getBody();
        List<Product> products = new ArrayList<>();
        if(productDTOs != null){
            for(ProductDTO productDTO : productDTOs) {
                products.add(productMapper.convertProductDTOToProduct(productDTO));
            }
        }
        return products;
    }

    public Product addProduct(Product product) {
        ProductDTO productDTO = restTemplate.postForEntity("https://fakestoreapi.com/products", productMapper.convertProducttoProductDTO(product), ProductDTO.class).getBody();
        return productDTO == null ? null : productMapper.convertProductDTOToProduct(productDTO);
    }

    public Optional<Product> updateProduct(Long productId, Product product) {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ProductDTO productDTO = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}" , productMapper.convertProducttoProductDTO(product), ProductDTO.class, productId);
        return Optional.ofNullable(productMapper.convertProductDTOToProduct(productDTO));
    }

    public Optional<Product> replaceProduct(Long productId, Product product){
        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(productMapper.convertProducttoProductDTO(product));
        ProductDTO productDTO = restTemplate.exchange("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, requestEntity, ProductDTO.class, productId).getBody();
        return Optional.ofNullable(productMapper.convertProductDTOToProduct(productDTO));
    }

    public void deleteProduct(Long productId) {
        restTemplate.delete("https://fakestoreapi.com/products/{id}", productId);
    }

}
