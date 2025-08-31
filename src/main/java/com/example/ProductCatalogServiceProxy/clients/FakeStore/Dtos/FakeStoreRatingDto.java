package com.example.ProductCatalogServiceProxy.clients.FakeStore.Dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FakeStoreRatingDto {
    private Double rating;
    private   Long count;
}
