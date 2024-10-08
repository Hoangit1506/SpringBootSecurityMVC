package com.springweb.SpringBootSecurityMVC.paging;

/*
 * How to pass an object from one controller to another controller.
 * Using CurrentPageTransporter to share between controllers.
 */

public class CurrentPageTransporter {
	private static int currentPage;

	public static int getCurrentPage() {
		return currentPage;
	}

	public static void setCurrentPage(int currentPage) {
		CurrentPageTransporter.currentPage = currentPage;
	}
	
}
