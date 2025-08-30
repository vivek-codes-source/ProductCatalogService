package com.example.ProductCatalogServiceProxy.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class Categery  extends BaseModel{

    private String name;
    private String description;
    private List<Product> products;
}
