package org.sidkr.productservice.services;

import org.sidkr.productservice.clients.FakeStoreClient;
import org.sidkr.productservice.exceptions.ExternalServiceException;
import org.sidkr.productservice.exceptions.ResourceNotFoundException;
import org.sidkr.productservice.models.Product;
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
        if (productOptional.isEmpty())
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        Product exsitingProduct = productOptional.get();
        if(product.getTitle() != null)
            exsitingProduct.setTitle(product.getTitle());
        if(product.getDescription() != null)
            exsitingProduct.setDescription(product.getDescription());
        if(product.getPrice() != null)
            exsitingProduct.setPrice(product.getPrice());
        if(product.getRating() != null)
            exsitingProduct.setRating(product.getRating());
        if(product.getCategory() != null)
            exsitingProduct.setCategory(product.getCategory());
        return fakeStoreClient.updateProduct(productId, exsitingProduct).orElseThrow(
                () -> new ExternalServiceException("External service error")
        );
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
