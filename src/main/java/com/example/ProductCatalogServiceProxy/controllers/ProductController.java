package com.example.ProductCatalogServiceProxy.controllers;

import com.example.ProductCatalogServiceProxy.Dto.ProductDto;
import com.example.ProductCatalogServiceProxy.models.Categery;
import com.example.ProductCatalogServiceProxy.models.Product;
import com.example.ProductCatalogServiceProxy.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService =   productService;
    }
    @GetMapping("/product")
    public List<Product> getProduct(){

       return productService.getProducts();
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
      try{  if (id < 1) {
            throw new IllegalArgumentException("product Id must be greater than 0");
        }

        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);

    } catch (Exception exception) {
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          throw  exception;
    }

    }
    @PostMapping("/product")
    public Product createProduct( @RequestBody ProductDto productDto){
        Product product = getproduct(productDto);
return productService.createProduct(product);
    }
@PatchMapping("/product/{id}")
    public Product updateProduct (@PathVariable Long id, @RequestBody ProductDto productDto){
   Product product = getproduct(productDto);
   return productService.updateProduct(id, product);


}


private Product getproduct (ProductDto productDto ){
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

}
