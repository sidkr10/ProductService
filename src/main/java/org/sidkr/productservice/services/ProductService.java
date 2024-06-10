package org.sidkr.productservice.services;

import org.sidkr.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(Long id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long id);
    Product replaceProduct(Long productId, Product product);
}
