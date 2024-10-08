package com.springweb.SpringBootSecurityMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityMvcApplication {

	public static void main(String[] args) {
		// ApplicationContext represent the Spring IoC container.
		SpringApplication.run(SpringBootSecurityMvcApplication.class, args);
		
		
//		ApplicationContext context = SpringApplication.run(SpringWebMVCSecurityCartApplication.class, args);
//
//		System.out.println("=========== List all Spring Beans =============== ");
//		String[] beans = context.getBeanDefinitionNames();
//		for (String bean : beans)
//			System.out.println(bean);
//		System.out.println("\n=================================================== ");
	}

}
