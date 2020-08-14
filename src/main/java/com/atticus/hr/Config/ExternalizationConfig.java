package com.atticus.hr.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ExternalizationConfig implements WebMvcConfigurer{
	@Bean(name="messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle=
				new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:messages/messages");
		messageBundle.setDefaultEncoding("UTF-8");
		
		return messageBundle;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/api/**")
	        .allowedOrigins("http://localhost:8080")
	        .allowedMethods("*")
	        .allowedHeaders("*")
	        .allowCredentials(false)
	        .maxAge(3600);
	}
	
}
