package com.example.ProductApi.repository;

import com.example.ProductApi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
