package org.sidkr.productservice.clients;

import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.models.Category;
import org.sidkr.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreClient {

    private final RestTemplate restTemplate;

    public FakeStoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProduct(long productId) {
        ProductDTO productDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDTO.class, productId).getBody();
        return productDTO == null ? null : convertProductDTOToProduct(productDTO);
    }

    public List<Product> getAllProducts(){
        ProductDTO[] productDTOs = restTemplate.getForEntity("https://fakestoreapi.com/products" , ProductDTO[].class).getBody();
        List<Product> products = new ArrayList<>();
        if(productDTOs != null){
            for(ProductDTO productDTO : productDTOs) {
                products.add(convertProductDTOToProduct(productDTO));
            }
        }
        return products;
    }

    public Product addProduct(Product product) {
        ProductDTO productDTO = restTemplate.postForEntity("https://fakestoreapi.com/products", convertProducttoProductDTO(product), ProductDTO.class).getBody();
        return productDTO == null ? null : convertProductDTOToProduct(productDTO);
    }

    public Product updateProduct(Long productId, Product product) {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ProductDTO productDTO = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}" , convertProducttoProductDTO(product), ProductDTO.class, productId);
        return productDTO == null ? null : convertProductDTOToProduct(productDTO);
    }

    public Product replaceProduct(Long productId, Product product){
        HttpEntity<ProductDTO> requestEntity = new HttpEntity<>(convertProducttoProductDTO(product));
        ProductDTO productDTO = restTemplate.exchange("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, requestEntity, ProductDTO.class, productId).getBody();
        return productDTO == null ? null : convertProductDTOToProduct(productDTO);
    }

    public void deleteProduct(Long productId) {
        restTemplate.delete("https://fakestoreapi.com/products/{id}", productId);
    }

    private ProductDTO convertProducttoProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setRating(product.getRating());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setImage(product.getImageUrl());
        return productDTO;
    }

    private Product convertProductDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setRating(productDTO.getRating());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDTO.getImage());
        return product;
    }

}
