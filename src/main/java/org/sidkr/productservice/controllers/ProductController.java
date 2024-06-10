package org.sidkr.productservice.controllers;

import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.models.Category;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.services.FakeStoreProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final FakeStoreProductService fakeStoreProductService;

    public ProductController(FakeStoreProductService fakeStoreProductService) {
        this.fakeStoreProductService = fakeStoreProductService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id) {
        return fakeStoreProductService.getProduct(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return fakeStoreProductService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductDTO productDTO) {
        return fakeStoreProductService.addProduct(convertProductDTOToProduct(productDTO));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        return fakeStoreProductService.replaceProduct(id, convertProductDTOToProduct(productDTO));
    }

    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        return fakeStoreProductService.updateProduct(id, convertProductDTOToProduct(productDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        fakeStoreProductService.deleteProduct(id);
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
