package com.example.ProductCatalogServiceProxy.services;

import com.example.ProductCatalogServiceProxy.clients.FakeStore.Dtos.FakeStoreProductDto;
import com.example.ProductCatalogServiceProxy.clients.FakeStore.Dtos.FakeStoreRatingDto;
import com.example.ProductCatalogServiceProxy.clients.FakeStore.client.FakeStoreApiClient;
import com.example.ProductCatalogServiceProxy.models.Categery;
import com.example.ProductCatalogServiceProxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;
    FakeStoreApiClient fakeStoreApiClient;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override

    public List<Product> getProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate
                .getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class)
                .getBody();

        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDto productDto : fakeStoreProductDtos){
            productList.add(getproduct(productDto));  // ✅ correct method
        }
        return productList;
    }



    @Override
    public Product getProduct(Long productid){
        return getproduct(fakeStoreApiClient.getproduct(productid));
    }

    @Override
    public Product createProduct(Product product){
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProductDto(product);
       FakeStoreProductDto fakeStoreProductDtoResponseEntity = fakeStoreApiClient.createProduct(fakeStoreProductDto);
        return getproduct(fakeStoreProductDtoResponseEntity);
    }

    @Override
    public Product updateProduct(Long id, Product product)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto=restTemplate.patchForObject("https://fakestoreapi.com/products/{id}",product,FakeStoreProductDto.class,id);
        Product resultantproduct=getproduct(fakeStoreProductDto);
        return resultantproduct;
    }

    private  Product getproduct(FakeStoreProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());

        Categery categery = new Categery();
        categery.setName(productDto.getCategory().getName());
        product.setCategory(categery);
//
        product.setId(productDto.getId());
        return product;
    }
    private FakeStoreProductDto getFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setCategory(product.getCategory());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        return fakeStoreProductDto;
    }



}
