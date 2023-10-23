package com.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("Jay Shree Radhe Krishna");
	}
		
	@Bean
	//for configuration of modelmapper so as we can map models with one another
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
