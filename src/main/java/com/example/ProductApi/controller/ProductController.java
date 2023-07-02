package com.example.ProductApi.controller;

import com.example.ProductApi.dto.ProductDto;
import com.example.ProductApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProductController {
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductDetail(@PathVariable("id") int id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("product/{id}/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") int id){
        return new ResponseEntity<>(productService.updateProduct(productDto, id), HttpStatus.OK);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete Product Successfully!!! ");
    }
}
