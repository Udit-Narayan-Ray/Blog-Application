package com.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.payloads.UserDefinedConstant;
import com.blog.services.ImageUploadService;
import com.blog.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService postService;

	@Autowired
	private ImageUploadService imageUploadService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/post/category/{cid}/user/{uid}")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO,
			@PathVariable("cid") Integer categoryId, @PathVariable("uid") Integer userId) {
		PostDTO createdPostDTO = this.postService.createPost(postDTO, userId, categoryId);

		return new ResponseEntity<PostDTO>(createdPostDTO, HttpStatus.CREATED);
	}

	@PutMapping("/put/{pid}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO,
			@PathVariable("pid") Integer postId) {
		PostDTO updatedPostDTO = this.postService.updatePost(postDTO, postId);

		return ResponseEntity.ok(updatedPostDTO);
	}

	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable("pid") Integer postId) {
		this.postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Post deleted successfully with PostId :- " + postId, true), HttpStatus.OK);
	}

	@GetMapping("/get/{pid}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("pid") Integer postId) {
		PostDTO postDTO = this.postService.getPostById(postId);

		return ResponseEntity.ok(postDTO);
	}

	@GetMapping("/get")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = UserDefinedConstant.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = UserDefinedConstant.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = UserDefinedConstant.SORT_BY, required = false) String sortBy) {

		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy);

		return ResponseEntity.ok(postResponse);
	}

	@GetMapping("/get/category/{cid}")
	public ResponseEntity<PostResponse> getPostsByCategoryId(@PathVariable("cid") Integer categoryId,
			@RequestParam(value = "pageNumber", defaultValue = UserDefinedConstant.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = UserDefinedConstant.PAGE_SIZE, required = false) int pageSize) {

		PostResponse postResponse = this.postService.getPostsByCategoryId(categoryId, pageNumber, pageSize);

		return ResponseEntity.ok(postResponse);
	}

	@GetMapping("/get/user/{uid}")
	public ResponseEntity<PostResponse> getPostsByUserId(@PathVariable("uid") Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = UserDefinedConstant.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = UserDefinedConstant.PAGE_SIZE, required = false) int pageSize) {

		PostResponse postResponse = this.postService.getPostsByUserId(userId, pageNumber, pageSize);

		return ResponseEntity.ok(postResponse);
	}

	@GetMapping("/search") // we can also do this with pathvariable
	public ResponseEntity<List<PostDTO>> findByTitleContaining(@RequestParam("key") String keyword) {
		List<PostDTO> postDTOs = this.postService.findByTitleContaining(keyword);

		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}

	@PostMapping("/post/uploadimage/{pid}")
	public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable("pid") Integer postId) throws IOException {

		PostDTO postDTO = this.postService.getPostById(postId);
		String imageUpload = this.imageUploadService.uploadImage(path, image);

		postDTO.setImageName(imageUpload);
		PostDTO updatedPostDTO = this.postService.updatePost(postDTO, postId);

		return ResponseEntity.ok(updatedPostDTO);
	}

	// method to view the uploaded image
	@GetMapping(value = "/get/image/{imgName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable String imgName, HttpServletResponse response) throws IOException {

		InputStream image=this.imageUploadService.getImage(path,imgName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(image,response.getOutputStream());
	}
}
