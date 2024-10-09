package com.springweb.SpringBootSecurityMVC.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springweb.SpringBootSecurityMVC.exception.NotEnoughProductsInStockException;
import com.springweb.SpringBootSecurityMVC.model.Product;
import com.springweb.SpringBootSecurityMVC.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	private ProductRepository productRepository;
	
	private Map<Product, Integer> products = new HashMap<>();
	
	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		if(products.containsKey(product)) { //If product is in the map just increment quantity by 1
			products.replace(product, products.get(product) + 1);
		}
		else {
			products.put(product, 1);  //it with quantity 1
		}
	}

	@Override
	public void removeProduct(Product product) {
		// TODO Auto-generated method stub
		if(products.containsKey(product)) {
			if(products.get(product) > 1) {  //just decrement quantity by 1
				products.replace(product, products.get(product) - 1);
			}
			else if(products.get(product) == 1){ //remove it from map
				products.remove(product);
			}
		}
	}

	@Override
	public Map<Product, Integer> getProductsInCart() {
		// TODO Auto-generated method stub
		//The real map is products, so you can modify value of the products value (in back-end),
		// But user (in view/ front-end) can not modify in Collections.unmodifiableMap(products)

		return Collections.unmodifiableMap(products); // unmodifiable copy of the map.
	}

	@Override
	public void checkout() throws NotEnoughProductsInStockException {
		// TODO Auto-generated method stub
		Product product;
		for(Map.Entry<Product, Integer> entry : products.entrySet()) {
			// Refresh quantity for every product before checking
			product = productRepository.getReferenceById(entry.getKey().getId());
			
			if(product.getQuantity() < entry.getValue()) {
				throw new NotEnoughProductsInStockException(product);
			}
			
			entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
		}
		productRepository.saveAll(products.keySet());
		productRepository.flush();
		
		//Nên quản lý session để chọn phương thức thanh toán hoặc kết thúc giao dịch ở đây.
	}

	@Override
	public BigDecimal getTotal() {
		// TODO Auto-generated method stub
		return products.entrySet().stream().map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue()))).reduce(BigDecimal :: add).orElse(BigDecimal.ZERO);
		
	}

}
