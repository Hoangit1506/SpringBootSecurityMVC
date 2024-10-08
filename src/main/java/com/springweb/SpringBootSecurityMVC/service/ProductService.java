package com.springweb.SpringBootSecurityMVC.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springweb.SpringBootSecurityMVC.model.Product;

public interface ProductService {
	Optional<Product> findById(Long id);
	Page<Product> findAllProductsPageable(Pageable pageable);
}
