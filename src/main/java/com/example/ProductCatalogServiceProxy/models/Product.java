package com.example.ProductCatalogServiceProxy.models;

import lombok.Setter;

@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Categery category;
}
