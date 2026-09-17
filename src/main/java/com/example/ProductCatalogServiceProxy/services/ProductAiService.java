package com.example.ProductCatalogServiceProxy.services;

import com.example.ProductCatalogServiceProxy.clients.FakeStore.Dtos.FakeStoreProductDto;
import com.example.ProductCatalogServiceProxy.clients.FakeStore.client.FakeStoreApiClient;
import com.example.ProductCatalogServiceProxy.models.Product;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductAiService {

    private final ChatClient chatClient;
    private final FakeStoreApiClient fakeStoreApiClient;
    private final FakeStoreProductService fakeStoreProductService;

    public ProductAiService(
            ChatClient.Builder builder,
            FakeStoreApiClient fakeStoreApiClient, FakeStoreProductService fakeStoreProductService
    ) {
        this.chatClient = builder.build();
        this.fakeStoreApiClient = fakeStoreApiClient;
        this.fakeStoreProductService = fakeStoreProductService;
    }

    public String askGemini(String question) {

        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

    public String askAboutProduct(Long productId, String question) {

        // 1. Get product from Fake Store API
        FakeStoreProductDto product =
                fakeStoreApiClient.getproduct(productId);

        // 2. Give product information + user's question to Gemini
        String prompt = """
                You are a product assistant.

                Product details:
                Product ID: %s
                Product Name: %s
                Description: %s
                Price: %s
                Category: %s

                User question:
                %s

                Answer the user's question based on the product details provided above.
                Do not invent product information.
                """.formatted(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                question
        );

        // 3. Gemini generates the answer
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    public String recommendProducts(String question) {

        // 1. Get all products from Fake Store
        List<Product> products = fakeStoreProductService.getProducts();

        // 2. Filter products in Java
        List<Product> filteredProducts = products.stream()
                .filter(product -> product.getPrice() <= 2000)
                .toList();

        // 3. Send only filtered products to Gemini
        String productData = filteredProducts.stream()
                .map(product ->
                        "ID: " + product.getId()
                                + ", Title: " + product.getTitle()
                                + ", Price: " + product.getPrice()
                                + ", Description: " + product.getDescription()
                )
                .collect(Collectors.joining("\n"));

        // 4. Create prompt
        String prompt = """
            You are a product recommendation assistant.

            User question:
            %s

            Available products:
            %s

            Recommend suitable products based only on
            the available product information.

            Briefly explain why the product matches
            the user's requirement.

            Do not invent product information.
            """.formatted(question, productData);

        // 5. Send filtered data to Gemini
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}