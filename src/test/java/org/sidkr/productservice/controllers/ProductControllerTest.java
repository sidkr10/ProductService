package org.sidkr.productservice.controllers;

import org.junit.jupiter.api.Test;
import org.sidkr.productservice.config.MapperConfig;
import org.sidkr.productservice.models.Category;
import org.sidkr.productservice.models.Product;
import org.sidkr.productservice.models.Rating;
import org.sidkr.productservice.services.ProductService;
import org.sidkr.productservice.utility.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@Import({MapperConfig.class,ProductMapper.class})
public class ProductControllerTest {

    @MockitoBean(name = "productCatalogueService")
    @SuppressWarnings("unused")
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_add_product() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setImageUrl("Test Image");
        Category category = new Category();
        category.setName("Test Category");
        product.setCategory(category);
        Rating rating = new Rating();
        rating.setCount(5);
        rating.setRate(4.5);
        product.setRating(rating);

        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Product\",\"description\":\"Test Description\",\"price\":100.0,\"category\":\"Test Category\",\"rating\":{\"rate\":4.5,\"count\":5}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.title").value(product.getTitle()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andExpect(jsonPath("$.category").value(product.getCategory().getName()));
    }

    @Test
    public void test_getProductById_valid() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");
        product.setDescription("Test Description");

        when(productService.getProduct(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Product"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    public void test_getProductById_invalid() throws Exception {
        when(productService.getProduct(anyLong())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details").value("Product id must be a positive integer"));
    }

    @Test
    public void test_addProduct_valid() throws Exception {
        Product product = new Product();
        product.setTitle("New Product");
        product.setDescription("New Description");

        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Product\",\"description\":\"New Description\",\"price\":100.0,\"category\":\"Test Category\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Product"))
                .andExpect(jsonPath("$.description").value("New Description"));
    }

    @Test
    public void test_updateProduct_valid() throws Exception {
        Long productId = 1L;

        Product product = new Product();
        product.setTitle("Updated Product");

        when(productService.replaceProduct(eq(productId), any(Product.class)))
                .thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Product\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Product"));
    }

    @Test
    public void test_patchProduct_valid() throws Exception {
        Long productId = 1L;

        Product product = new Product();
        product.setTitle("Patched Product");

        when(productService.updateProduct(eq(productId), any(Product.class)))
                .thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Patched Product\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Patched Product"));
    }

    @Test
    public void test_deleteProduct_valid() throws Exception {
        Long productId = 1L;

        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", productId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test_deleteProduct_invalid() throws Exception {
        Long productId = 0L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", productId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details").value("Product id must be a positive integer"));
    }

    @Test
    public void test_getAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");

        List<Product> products = List.of(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
