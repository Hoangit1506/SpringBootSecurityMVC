package com.springweb.SpringBootSecurityMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.springweb.SpringBootSecurityMVC.exception.NotEnoughProductsInStockException;
import com.springweb.SpringBootSecurityMVC.paging.CurrentPageTransporter;
import com.springweb.SpringBootSecurityMVC.service.ProductService;
import com.springweb.SpringBootSecurityMVC.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ProductService productService;
	
	@GetMapping("/shoppingCart")
	public ModelAndView shoppingCart() {
		ModelAndView modelAndView = new ModelAndView("/shoppingCart");
		modelAndView.addObject("products", shoppingCartService.getProductsInCart());
		modelAndView.addObject("total", shoppingCartService.getTotal().toString());
		
		return modelAndView;
	}
	
	//Sau khi click "BUY" button to buy a product, then redirect to exact the current page of products list.
	@GetMapping("/shoppingCart/addProduct/{productId}")
	public String addProductToCartStayCurrentPage(@PathVariable("productId") Long productId) {
		productService.findById(productId).ifPresent(shoppingCartService::addProduct);
		int qsCurrentPage = CurrentPageTransporter.getCurrentPage();
		
		return "redirect:/products" + "?page=" + qsCurrentPage;
	}
	
	/*
	 * Sau khi click "BUY" button to buy a product, then redirect to the first of page list of products.
	 * @GetMapping("/shoppingCart/addProduct/{productId}")
	 * public String addProductToCart(@PathVariable("productId") Long productId){
	 * 	productService.findById(productId).ifPresent(shoppingCartService::addProduct);
	 * 	return "redirect:/products";
	 * }
	 */
	
	@GetMapping("/shoppingCart/removeProduct/{productId}")
	public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
		productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
		return shoppingCart();
	}
	
	@GetMapping("/shoppingCart/checkout")
	public ModelAndView checkoutCart() {
		ModelAndView modelAndView = new ModelAndView("/shoppingCart");
		modelAndView.addObject("products", shoppingCartService.getProductsInCart());
		modelAndView.addObject("total", shoppingCartService.getTotal().toString());
		
		try {
			shoppingCartService.checkout();
			modelAndView.addObject("success", "You have checked out successfully.");
		} catch (NotEnoughProductsInStockException e) {
			modelAndView.addObject("error", "Sorry, there is not enough quantity for your order.");
			return shoppingCart().addObject("outOfStockMessage", e.getMessage());
		}
		
		return modelAndView;
	}
	
}
