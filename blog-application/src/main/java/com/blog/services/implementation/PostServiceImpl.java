package com.blog.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category", categoryId));
		Post post = this.modelMapper.map(postDTO, Post.class);

		post.setPostedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.postRepo.save(post);

		return this.modelMapper.map(savedPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		Post savedPost = this.postRepo.save(post);

		return this.modelMapper.map(savedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post_id", postId));

		this.postRepo.delete(post);
	}

	@Override
	public PostDTO getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> page = this.postRepo.findAll(pageable);
		List<Post> posts = page.getContent();

		List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setPostDTOs(postDTOs);
		postResponse.setLast(page.isLast());
		postResponse.setPageNumber(page.getNumber() + 1);// actual page number with index 1
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalElement(page.getTotalElements());
		postResponse.setTotalPage(page.getTotalPages());

		return postResponse;
	}

	@Override
	public PostResponse getPostsByUserId(Integer userId, int pageNumber, int pageSize) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user_id", userId));

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> page = this.postRepo.findByUser(user, pageable);
		List<Post> posts = page.getContent();

		List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setPostDTOs(postDTOs);
		postResponse.setPageNumber(page.getNumber() + 1);
		postResponse.setPageSize(page.getSize());
		postResponse.setTotalPage(page.getTotalPages());
		postResponse.setTotalElement(page.getTotalElements());
		postResponse.setLast(page.isLast());

		return postResponse;
	}

	@Override
	public PostResponse getPostsByCategoryId(Integer categoryId, int pageNumber, int pageSize) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category_id", categoryId));

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Post> page = this.postRepo.findByCategory(category, pageable);
		List<Post> posts = page.getContent();

		List<PostDTO> postDTOs = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setLast(page.isLast());
		postResponse.setPageNumber(page.getNumber() + 1);
		postResponse.setPageSize(page.getSize());
		postResponse.setPostDTOs(postDTOs);
		postResponse.setTotalPage(page.getTotalPages());
		postResponse.setTotalElement(page.getTotalElements());

		return postResponse;
	}

	@Override
	public List<PostDTO> findByTitleContaining(String key) {

		List<Post> posts = this.postRepo.findByTitleContaining(key);
		List<PostDTO> searchResult = posts.stream().map((search) -> this.modelMapper.map(search, PostDTO.class))
				.collect(Collectors.toList());

		return searchResult;
	}

}
