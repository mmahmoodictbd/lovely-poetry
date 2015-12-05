package com.chumbok.poetry.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String path = System.getProperty("user.home") + File.separator
				+ ".LovelyPoetry/";
		registry.addResourceHandler("/**").addResourceLocations("file:" + path);
	}

}