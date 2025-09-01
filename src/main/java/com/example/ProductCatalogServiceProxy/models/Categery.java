package com.example.ProductCatalogServiceProxy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Categery  extends BaseModel{

    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
