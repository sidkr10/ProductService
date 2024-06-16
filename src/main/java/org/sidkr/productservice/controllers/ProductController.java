package org.sidkr.productservice.controllers;

import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.exceptions.BadRequestException;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.services.ProductService;
import org.sidkr.productservice.utility.ProductMapperUtility;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapperUtility productMapper;

    public ProductController(@Qualifier("productCatalogueService") ProductService productService, ProductMapperUtility productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        return productMapper.convertProducttoProductDTO(productService.getProduct(id));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productMapper.convertProducttoProductDTO(productService.addProduct(productMapper.convertProductDTOToProduct(productDTO)));
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        Product product = productMapper.convertProductDTOToProduct(productDTO);
        return productMapper.convertProducttoProductDTO(productService.replaceProduct(id,product));
    }

    @PatchMapping("/{id}")
    public ProductDTO patchProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        Product product = productMapper.convertProductDTOToProduct(productDTO);
        return productMapper.convertProducttoProductDTO(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        productService.deleteProduct(id);
    }

}
