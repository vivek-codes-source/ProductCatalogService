package com.example.ProductCatalogServiceProxy.services;

import com.example.ProductCatalogServiceProxy.clients.FakeStore.Dtos.FakeStoreProductDto;
import com.example.ProductCatalogServiceProxy.clients.FakeStore.client.FakeStoreApiClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FakeStoreRagService {

    private final FakeStoreApiClient fakeStoreApiClient;
    private final VectorStore vectorStore;
    private final ChatClient chatClient;

    public FakeStoreRagService(
            FakeStoreApiClient fakeStoreApiClient,
            VectorStore vectorStore,
            ChatClient chatClient
    ) {
        this.fakeStoreApiClient = fakeStoreApiClient;
        this.vectorStore = vectorStore;
        this.chatClient = chatClient;
    }

    public void indexFakeStoreProducts() {

        List<FakeStoreProductDto> products =
                fakeStoreApiClient.getAllProducts();

        List<Document> documents = products.stream()
                .map(this::createDocument)
                .toList();

        vectorStore.add(documents);
    }

    public List<Document> searchProducts(String query) {

        return vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(5)
                        .build()
        );
    }

    public String ask(String query) {

        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(5)
                        .build()
        );

        String context = documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        return chatClient.prompt()
                .system("""
                        You are a helpful product assistant.

                        Answer the user's question using only the provided
                        product context.

                        If the answer is not available in the context,
                        say that you don't have enough information.
                        """)
                .user("""
                        Product Context:
                        %s

                        User Question:
                        %s
                        """.formatted(context, query))
                .call()
                .content();
    }

    private Document createDocument(FakeStoreProductDto product) {

        String content = """
                Product ID: %s
                Product Name: %s
                Category: %s
                Price: %s
                Description: %s
                """.formatted(
                product.getId(),
                product.getTitle(),
                product.getCategory(),
                product.getPrice(),
                product.getDescription()
        );

        Map<String, Object> metadata = new HashMap<>();

        metadata.put("productId", product.getId());
        metadata.put("title", product.getTitle());
        metadata.put("category", product.getCategory());
        metadata.put("price", product.getPrice());

        return new Document(content, metadata);
    }
}