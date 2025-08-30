package com.example.ProductCatalogServiceProxy.services;

import com.example.ProductCatalogServiceProxy.Dto.ProductDto;
import com.example.ProductCatalogServiceProxy.models.Categery;
import com.example.ProductCatalogServiceProxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override

    public List<Product> getProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto[] productDtos = restTemplate
                .getForEntity("https://fakestoreapi.com/products", ProductDto[].class)
                .getBody();

        List<Product> productList = new ArrayList<>();
        for(ProductDto productDto : productDtos){
            productList.add(getproduct(productDto));  // ✅ correct method
        }
        return productList;
    }



    @Override
    public Product getProduct(Long productid){
        RestTemplate restTemplate = restTemplateBuilder.build();
      ProductDto productDto =  restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productid).getBody();
        if (productDto == null) {
            throw new RuntimeException("Product not found from FakeStore API");
        }

        return getproduct(productDto);
    }

    @Override
    public Product createProduct(ProductDto productDto){
       RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> responseEntity=restTemplate.postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
return getproduct(responseEntity.getBody());

    }

    @Override
    public String updateProduct(ProductDto productDto){
        return null;
    }

    private  Product getproduct(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());

        Categery categery = new Categery();
        categery.setName(productDto.getCategory());
        product.setCategory(categery);

        product.setId(productDto.getId());
        return product;
    }

}
