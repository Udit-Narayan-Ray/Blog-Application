package com.blog.services;

import java.util.List;

import com.blog.payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentDTO, Integer postId, Integer userId);

	void deleteComment(Integer commentId);
	
	List<CommentDTO> getCommentsByPostId(Integer postId);
}
