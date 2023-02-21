package com.youube.clone.youtubeclone.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("PUT", "GET", "POST", "DELETE", "PATCH", "OPTIONS").allowedHeaders("*").maxAge(3600);
	}
}
