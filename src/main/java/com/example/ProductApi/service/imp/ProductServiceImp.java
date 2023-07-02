package com.example.ProductApi.service.imp;

import com.example.ProductApi.dto.ProductDto;
import com.example.ProductApi.exceptions.ProductNotFoundException;
import com.example.ProductApi.models.Product;
import com.example.ProductApi.repository.ProductRepository;
import com.example.ProductApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    ProductRepository productRepository;
    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        Product newProduct  = productRepository.save(product);

        ProductDto productResponse = new ProductDto();
        productResponse.setId(newProduct.getId());
        productResponse.setName(newProduct.getName());
        productResponse.setPrice(newProduct.getPrice());

        return productResponse;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(int id) {
        Product products = productRepository.findById(id).orElseThrow(() ->new ProductNotFoundException("Product could not be found!!!"));
        return mapToDto(products);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, int id) {
        Product product = productRepository.findById(id).orElseThrow(() ->new ProductNotFoundException("Product could not be update!!!"));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        Product updateProduct = productRepository.save(product);

        return mapToDto(updateProduct);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() ->new ProductNotFoundException("Product could not be delete!!!"));
        productRepository.delete(product);
    }

    private ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    private Product mapToEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        return product;
    }
}
