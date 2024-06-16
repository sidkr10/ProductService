package org.sidkr.productservice.controllers;

import org.sidkr.productservice.dtos.ProductDTO;
import org.sidkr.productservice.exceptions.BadRequestException;
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
    public ProductDTO getProductById(@PathVariable("id") Long id) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        return productMapper.convertProducttoProductDTO(fakeStoreProductService.getProduct(id));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return fakeStoreProductService.getAllProducts();
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productMapper.convertProducttoProductDTO(fakeStoreProductService.addProduct(productMapper.convertProductDTOToProduct(productDTO)));
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        return productMapper.convertProducttoProductDTO(fakeStoreProductService.replaceProduct(id, productMapper.convertProductDTOToProduct(productDTO)));
    }

    @PatchMapping("/{id}")
    public ProductDTO patchProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        return productMapper.convertProducttoProductDTO(fakeStoreProductService.updateProduct(id, productMapper.convertProductDTOToProduct(productDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        if(id == null || id <= 0)
            throw new BadRequestException("Product id must be a positive integer");
        fakeStoreProductService.deleteProduct(id);
    }

}
