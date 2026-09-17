package com.example.ProductCatalogServiceProxy.controllers;

import com.example.ProductCatalogServiceProxy.services.ProductAiService;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/ai")
public class ProductAiController {

    private final ProductAiService productAiService;

    public ProductAiController(ProductAiService productAiService) {
        this.productAiService = productAiService;
    }

    @GetMapping("/ask")
    public String askGemini(@RequestParam String question) {
        return productAiService.askGemini(question);
    }

    @GetMapping("/product/{id}")
    public String askAboutProduct(
            @PathVariable Long id,
            @RequestParam String question
    ) {
        return productAiService.askAboutProduct(id, question);
    }

    @GetMapping("/products")
    public String recommendProducts(
            @RequestParam String question
    ) {
        return productAiService.recommendProducts(question);
    }
}