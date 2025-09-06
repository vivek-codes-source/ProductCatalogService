package com.example.ProductCatalogServiceProxy.controllers;

import com.example.ProductCatalogServiceProxy.models.Categery;
import com.example.ProductCatalogServiceProxy.models.Product;
import com.example.ProductCatalogServiceProxy.services.IProductService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_getProducts_ReceivesSuccessfulResponse() throws Exception {



        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setTitle("IPhone12");
        Product product2 = new Product();
        product2.setTitle("MacBook");
        productList.add(product1);
        productList.add(product2);

        when(productService.getProducts()).thenReturn(productList);

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("IPhone12"));
    }


    @Test
    public void Test_createProduct_ReceivesSuccessfulResponse() throws Exception {

        Categery category = new Categery();
        category.setName("Fruits");

        Product productToCreate = new Product();
        productToCreate.setId(1000L);
        productToCreate.setTitle("Orange");
        productToCreate.setDescription("Freshy and Juicy");
        productToCreate.setCategory(category);

        Product expectedProduct = new Product();
        expectedProduct.setId(1000L);
        expectedProduct.setTitle("Orange");
        expectedProduct.setDescription("Freshy and Juicy");
        expectedProduct.setCategory(category);

        when(productService.createProduct(any(Product.class))).thenReturn(expectedProduct);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)));
                // flexible check
//                .andExpect(jsonPath("$.id").value(10))
//                .andExpect(jsonPath("$.title").value("Orange"))
//                .andExpect(jsonPath("$.price").value(100.0));

    }
}
