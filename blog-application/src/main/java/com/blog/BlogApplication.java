package com.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.entities.Role;
import com.blog.payloads.UserDefinedConstant;
import com.blog.repositories.RoleRepo;

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
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
//		System.out.println(this.passwordEncoder.encode("radhe"));
		try {
			Role role=new Role();
			role.setRoleId(UserDefinedConstant.USER);
			role.setRole("ROLE_USER");
			
			Role role1=new Role();
			role1.setRoleId(UserDefinedConstant.ADMIN);
			role1.setRole("ROLE_ADMIN");
			
			System.out.println(this.roleRepo.save(role));
			System.out.println(this.roleRepo.save(role1));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
