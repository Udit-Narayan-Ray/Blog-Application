package com.blog.services;

import java.util.List;

import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;

public interface PostService {

	// creating post
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

	// updating post
	PostDTO updatePost(PostDTO postDTO, Integer postId);

	// deleting post
	void deletePost(Integer postId);

	// get post corresponding to the postid
	PostDTO getPostById(Integer postId);

	// get all post
	PostResponse getAllPosts(int pageNumber,int pageSize,String sortBy);

	// get all post corresponding to the user
	PostResponse getPostsByUserId(Integer userId,int pageNumber,int pageSize);

	// get all post with the category id
	PostResponse getPostsByCategoryId(Integer categoryId,int pageNumber,int pageSize);

	//	search the post with key
	List<PostDTO> findByTitleContaining(String key);

}
