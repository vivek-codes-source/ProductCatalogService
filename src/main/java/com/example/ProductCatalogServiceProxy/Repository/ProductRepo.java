package com.example.ProductCatalogServiceProxy.Repository;

import com.example.ProductCatalogServiceProxy.models.Product;
import com.example.ProductCatalogServiceProxy.services.IProductService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo  extends JpaRepository<Product, Long> {
    Product save(Product product);
}
