package com.apbg.webstore.productservice.repository;

import com.apbg.webstore.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
