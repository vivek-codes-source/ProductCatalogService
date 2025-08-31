package com.example.ProductCatalogServiceProxy.clients.FakeStore.Dtos;

import com.example.ProductCatalogServiceProxy.models.Categery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreProductDto {
    private Long  id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private Categery category;
    private FakeStoreRatingDto fakeStoreRatingDto;

}
