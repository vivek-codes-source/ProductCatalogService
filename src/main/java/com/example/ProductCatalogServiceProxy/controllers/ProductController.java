package com.example.ProductCatalogServiceProxy.controllers;

import com.example.ProductCatalogServiceProxy.Dto.ProductDto;
import com.example.ProductCatalogServiceProxy.models.Product;
import com.example.ProductCatalogServiceProxy.services.IProductService;
import com.example.ProductCatalogServiceProxy.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService =   productService;
    }
    @GetMapping("/product")
    public List<Product> getProduct(){

       return productService.getProducts();
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
      try{  if (id < 1) {
            throw new IllegalArgumentException("product Id must be greater than 0");
        }

        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    } catch (Exception exception) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }
    @PostMapping("/product")
    public Product createProduct( @RequestBody ProductDto productDto){
return productService.createProduct(productDto);
    }
@PatchMapping("/product")
    public String updateProduct( @RequestBody ProductDto productDto){
        return "returning product"+productDto;
}

}
