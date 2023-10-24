package com.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

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

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		
//		System.out.println(this.passwordEncoder.encode("radhe"));
		
	}
}
