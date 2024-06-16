package org.sidkr.productservice.services;

import org.sidkr.productservice.clients.FakeStoreClient;
import org.sidkr.productservice.exceptions.ResourceNotFoundException;
import org.sidkr.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> product = fakeStoreClient.getProduct(id);
        if (product.isEmpty())
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return fakeStoreClient.getAllProducts();
    }

    @Override
    public Product addProduct(Product product) {
        return fakeStoreClient.addProduct(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Optional<Product> productOptional = fakeStoreClient.getProduct(productId);
        if(productOptional.isEmpty())
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        return productOptional.get();
    }

    @Override
    public void deleteProduct(Long id) {
        fakeStoreClient.deleteProduct(id);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        Optional<Product> productOptional = fakeStoreClient.getProduct(productId);
        if(productOptional.isEmpty())
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        return productOptional.get();
    }

}
