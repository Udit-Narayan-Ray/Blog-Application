package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Comment;
import com.blog.entities.Post;

public interface CommentRepo extends JpaRepository<Comment, Integer> 
{
	List<Comment> findByPost(Post post);
}
