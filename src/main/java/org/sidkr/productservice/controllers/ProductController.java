package org.sidkr.productservice.controllers;

import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.exceptions.BadRequestException;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.services.ProductService;
import org.sidkr.productservice.utility.ProductMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(@Qualifier("productCatalogueService") ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        return productMapper.toDto(productService.getProduct(id));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDto(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDto(productService.replaceProduct(id,product));
    }

    @PatchMapping("/{id}")
    public ProductDTO patchProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDto(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long id) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        productService.deleteProduct(id);
    }

}
