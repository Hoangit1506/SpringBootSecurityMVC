package com.springweb.SpringBootSecurityMVC.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springweb.SpringBootSecurityMVC.model.Product;
import com.springweb.SpringBootSecurityMVC.paging.CurrentPageTransporter;
import com.springweb.SpringBootSecurityMVC.paging.Pager;
import com.springweb.SpringBootSecurityMVC.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	private static final int INITIAL_PAGE = 0;
	
	@GetMapping(value= {"/products"})
	public ModelAndView fetchProducts(@RequestParam("page") Optional<Integer> page) {
		
		// Evaluate page. If requested parameter is null or less than 0 (to prevent exception),
		// return initial size. Otherwise, return value of param decreased by 1.
		
		
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() -1; //pageNo
		
		//// To click BUY a product is still in current page,
		// we use the object CurrentPageTransporter to pass it into ShoppingCartController
		int currentPage = evalPage + 1;
		CurrentPageTransporter.setCurrentPage(currentPage);
		System.out.println("---------------SET qsCurrentPage--------- = "+ currentPage);
		
		Sort sort = Sort.by("Name").ascending();
		Page<Product> products = productService.findAllProductsPageable(PageRequest.of(evalPage, 5, sort));
		
		Pager pager = new Pager(products);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", products);
		modelAndView.addObject("pager", pager);
		modelAndView.addObject("/products");
		
		return modelAndView;
	}
}
