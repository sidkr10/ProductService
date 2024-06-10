package org.sidkr.productservice.services;

import org.sidkr.productservice.clients.FakeStoreClient;
import org.sidkr.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProduct(Long id) {
        return fakeStoreClient.getProduct(id);
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
        return fakeStoreClient.updateProduct(productId, product);
    }

    @Override
    public void deleteProduct(Long id) {
        fakeStoreClient.deleteProduct(id);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return fakeStoreClient.replaceProduct(productId, product);
    }

}
