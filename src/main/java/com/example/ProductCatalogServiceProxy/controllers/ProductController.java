package com.example.ProductCatalogServiceProxy.controllers;

import com.example.ProductCatalogServiceProxy.Dto.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @GetMapping("/product")
    public String getProduct(){
        return ("hello");
    }
    @PostMapping("/product")
    public String createProduct( @RequestBody ProductDto productDto){
        return "returning product"+productDto;
    }
@PatchMapping("/product")
    public String updateProduct( @RequestBody ProductDto productDto){
        return "returning product"+productDto;
}

}
