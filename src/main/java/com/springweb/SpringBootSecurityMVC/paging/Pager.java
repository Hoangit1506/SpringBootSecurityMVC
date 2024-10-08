package com.springweb.SpringBootSecurityMVC.paging;

import org.springframework.data.domain.Page;

import com.springweb.SpringBootSecurityMVC.model.Product;

/* 
 * Pager class, a utility class used for preparing pagination component, 
 * is used to wrap the result list of products.
 * 
 * @author Dusan Raljic
 */

public class Pager {
	private final Page<Product> products;	

    public Pager(Page<Product> products) {
        this.products = products;
    }

    public int getPageIndex() {
        return products.getNumber() + 1;
    }

    public int getPageSize() {
        return products.getSize();
    }

    public boolean hasNext() {
        return products.hasNext();
    }

    public boolean hasPrevious() {
        return products.hasPrevious();
    }

    public int getTotalPages() {
        return products.getTotalPages();     
    }

    public long getTotalElements() {
        return products.getTotalElements();
    }

    public boolean indexOutOfBounds() {
        return this.getPageIndex() < 0 || 
        		this.getPageIndex() > this.getTotalElements();
    }
}
