package com.course.ecommerce;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//los 2 asteriscos indican que voy a tomar todo lo que venga a continuacion de las carpetas /images
		registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
		
	}
}
