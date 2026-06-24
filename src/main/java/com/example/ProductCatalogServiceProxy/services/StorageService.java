package com.example.ProductCatalogServiceProxy.services;

import com.example.ProductCatalogServiceProxy.Dto.UserDto;
import com.example.ProductCatalogServiceProxy.Repository.ProductRepo;
import com.example.ProductCatalogServiceProxy.models.Product;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class StorageService implements IProductService {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
     Product resultantantProduct = productRepo.save(product);
     return resultantantProduct;
    }

    @Override
    public Product getProduct(Long productid) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
    public Product getProductDetail (Long userid, Long productid) {
        Product product = productRepo.findById(productid).get();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserDto> response = restTemplate.getForEntity(
                "http://localhost:9000/users/{id}",
                UserDto.class,
                userid
        );
        System.out.println(response.getBody().getEmail());

return product;

    }
}
