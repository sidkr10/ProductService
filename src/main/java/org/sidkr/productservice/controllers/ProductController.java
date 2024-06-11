package org.sidkr.productservice.controllers;

import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.services.FakeStoreProductService;
import org.sidkr.productservice.utility.ProductMapperUtility;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final FakeStoreProductService fakeStoreProductService;
    private final ProductMapperUtility productMapper;

    public ProductController(FakeStoreProductService fakeStoreProductService, ProductMapperUtility productMapper) {
        this.fakeStoreProductService = fakeStoreProductService;
        this.productMapper = productMapper;
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
        return fakeStoreProductService.addProduct(productMapper.convertProductDTOToProduct(productDTO));
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        return fakeStoreProductService.replaceProduct(id, productMapper.convertProductDTOToProduct(productDTO));
    }

    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        return fakeStoreProductService.updateProduct(id, productMapper.convertProductDTOToProduct(productDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        fakeStoreProductService.deleteProduct(id);
    }

}
