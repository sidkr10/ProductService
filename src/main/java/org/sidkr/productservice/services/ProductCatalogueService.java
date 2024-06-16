package org.sidkr.productservice.services;

import org.sidkr.productservice.exceptions.ResourceNotFoundException;
import org.sidkr.productservice.models.Category;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.models.Rating;
import org.sidkr.productservice.repositories.CategoryRepository;
import org.sidkr.productservice.repositories.ProductRepository;
import org.sidkr.productservice.repositories.RatingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productCatalogueService")
public class ProductCatalogueService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository productCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final RatingsRepository ratingsRepository;

    public ProductCatalogueService(ProductRepository productRepository, CategoryRepository productCategoryRepository, CategoryRepository categoryRepository, RatingsRepository ratingsRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.ratingsRepository = ratingsRepository;
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty())
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        saveAndSetCategory(product);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty())
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        Product updatedProduct = optionalProduct.get();
        if(product.getTitle() != null)
            updatedProduct.setTitle(product.getTitle());
        if(product.getDescription() != null)
            updatedProduct.setDescription(product.getDescription());
        if(product.getPrice() != null)
            updatedProduct.setPrice(product.getPrice());
        if(product.getRating() != null){
            saveAndSetRatings(updatedProduct,updatedProduct.getRating().getId());
        }
        if(product.getCategory() != null){
            updatedProduct.setCategory(product.getCategory());
            saveAndSetCategory(updatedProduct);
        }
        return productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty())
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        productRepository.deleteById(id);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty())
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        product.setId(productId);
        saveAndSetRatings(product, optionalProduct.get().getRating().getId());
        saveAndSetCategory(product);
        return productRepository.save(product);
    }

    private void saveAndSetCategory(Product product) {
        Optional<Category> category = categoryRepository.findCategoriesByName(product.getCategory().getName());
        if(category.isEmpty()){
            Category savedCategory = productCategoryRepository.save(product.getCategory());
            product.setCategory(savedCategory);
        }else{
            product.setCategory(category.get());
        }
    }

    private void saveAndSetRatings(Product product, Long ratingId) {
        Optional<Rating> ratings = ratingsRepository.findRatingById(ratingId);
        if(ratings.isEmpty()){
            Rating savedRatings = ratingsRepository.save(product.getRating());
            product.setRating(savedRatings);
        }else{
            Rating newRating = new Rating();
            newRating.setId(ratingId);
            if(product.getRating()!=null){
                newRating.setRate(product.getRating().getRate());
                newRating.setCount(product.getRating().getCount());
            }
            product.setRating(newRating);
        }
    }
}
