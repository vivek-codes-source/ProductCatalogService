package com.example.ProductCatalogServiceProxy.services;

import com.example.ProductCatalogServiceProxy.Repository.ProductRepo;
import com.example.ProductCatalogServiceProxy.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StorageService implements IProductService {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
     Product resultantantProduct = productRepo.save(product);
     return resultantantProduct;
    }

    @Override
    public Product getProduct(Long productid) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
