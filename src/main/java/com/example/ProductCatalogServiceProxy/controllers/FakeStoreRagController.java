package com.example.ProductCatalogServiceProxy.controllers;

import com.example.ProductCatalogServiceProxy.services.FakeStoreRagService;
import org.springframework.ai.document.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rag")
public class FakeStoreRagController {

    private final FakeStoreRagService fakeStoreRagService;

    public FakeStoreRagController(FakeStoreRagService fakeStoreRagService) {
        this.fakeStoreRagService = fakeStoreRagService;
    }

    @PostMapping("/index")
    public ResponseEntity<String> indexProducts() {

        fakeStoreRagService.indexFakeStoreProducts();

        return ResponseEntity.ok("FakeStore products indexed successfully");
    }

    @PostMapping("/search")
    public ResponseEntity<List<Document>> searchProducts(
            @RequestParam String query
    ) {
        return ResponseEntity.ok(
                fakeStoreRagService.searchProducts(query)
        );
    }

    @PostMapping("/ask")
    public ResponseEntity<String> ask(
            @RequestParam String query
    ) {
        return ResponseEntity.ok(
                fakeStoreRagService.ask(query)
        );
    }
}