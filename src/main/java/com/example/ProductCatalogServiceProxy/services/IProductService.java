package com.example.ProductCatalogServiceProxy.services;

import com.example.ProductCatalogServiceProxy.Dto.ProductDto;
import com.example.ProductCatalogServiceProxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product createProduct(ProductDto productDto);
     Product getProduct(Long productid);


    String updateProduct(ProductDto productDto);
}
