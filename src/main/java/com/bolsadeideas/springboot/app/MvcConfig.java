package com.bolsadeideas.springboot.app;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
	
	//private final Logger log= LoggerFactory.getLogger(getClass());

	//@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		
//		WebMvcConfigurer.super.addResourceHandlers(registry);
//		
//		String resourcePath=Paths.get("upload").toAbsolutePath().toUri().toString();
//		
//		registry.addResourceHandler("/uploads/**")
//		.addResourceLocations(resourcePath);
//		
//		log.info("routePath en MVC:" + resourcePath);
//	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("error_403");
	}
	
}
