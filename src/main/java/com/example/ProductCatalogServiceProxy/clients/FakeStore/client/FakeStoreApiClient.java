package com.example.ProductCatalogServiceProxy.clients.FakeStore.client;

import com.example.ProductCatalogServiceProxy.Dto.ProductDto;
import com.example.ProductCatalogServiceProxy.clients.FakeStore.Dtos.FakeStoreProductDto;
import com.example.ProductCatalogServiceProxy.clients.FakeStore.Dtos.FakeStoreRatingDto;
import com.example.ProductCatalogServiceProxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
@Component
public class FakeStoreApiClient {
    RestTemplateBuilder restTemplateBuilder;
    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public  FakeStoreProductDto getproduct(Long productid){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto =restTemplate.getForEntity("http://fakestoreapi.com/products/{id}",FakeStoreProductDto.class,productid).getBody();
      return fakeStoreProductDto;

    }
    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=restTemplate.postForEntity("http://fakestoreapi.com/products",fakeStoreProductDto,FakeStoreProductDto.class);
        return fakeStoreProductDtoResponseEntity.getBody();
    }


}
