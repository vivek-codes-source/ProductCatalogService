package com.example.ProductCatalogServiceProxy.services;

import com.example.ProductCatalogServiceProxy.Dto.ProductDto;
import com.example.ProductCatalogServiceProxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product createProduct(Product product);
     Product getProduct(Long productid);


    Product updateProduct(Long id,Product product);
}
