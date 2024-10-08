package com.springweb.SpringBootSecurityMVC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springweb.SpringBootSecurityMVC.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
}
