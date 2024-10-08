package com.springweb.SpringBootSecurityMVC.service;

import java.math.BigDecimal;
import java.util.Map;

import com.springweb.SpringBootSecurityMVC.exception.NotEnoughProductsInStockException;
import com.springweb.SpringBootSecurityMVC.model.Product;


public interface ShoppingCartService {
	void addProduct(Product product);
    void removeProduct(Product product);
    Map<Product, Integer> getProductsInCart();
    void checkout() throws NotEnoughProductsInStockException;
    BigDecimal getTotal();
}
