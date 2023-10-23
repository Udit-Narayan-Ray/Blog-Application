package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDTO;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/postid/{pid}/userid/{uid}")
	public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO,
			@PathVariable("pid") Integer postId, @PathVariable("uid") Integer userId) {

		CommentDTO createdComment = this.commentService.createComment(commentDTO, postId, userId);

		return new ResponseEntity<CommentDTO>(createdComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{cmtid}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("cmtid") Integer commentId) {

		this.commentService.deleteComment(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("!! Comment Deleted successfully !!", true),
				HttpStatus.OK);
	}
	
	@GetMapping("/get/{pid}")
	public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable("pid")Integer postId)
	{
		List<CommentDTO> allComments = this.commentService.getCommentsByPostId(postId);
		
		return ResponseEntity.ok(allComments);
	}
	
}
