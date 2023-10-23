package com.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	// these two methods are user defined as their would not any inbuilt function
	// for finding List of post
	// based on user and category objects but if we provide objects to JPA then it
	// will
	// automatically fetch for us and it behave same as findById(name of id) and we
	// have as there is relation of many to one for user and category
	//findBy<fieldName>() this can be the user define method

	Page<Post> findByUser(User user,Pageable pageable);

	Page<Post> findByCategory(Category category,Pageable pageable);
	
	List<Post> findByTitleContaining(String keyword);

}
