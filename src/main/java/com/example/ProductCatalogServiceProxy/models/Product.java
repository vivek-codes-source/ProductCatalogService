package com.example.ProductCatalogServiceProxy.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Categery category;
}
