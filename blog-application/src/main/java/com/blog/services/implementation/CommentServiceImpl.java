package com.blog.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDTO;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId, Integer userId) {

		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		comment.setUser(user);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		CommentDTO saveCommentDTO = this.modelMapper.map(savedComment, CommentDTO.class);

		return saveCommentDTO;
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));

		this.commentRepo.delete(comment);
	}

	@Override
	public List<CommentDTO> getCommentsByPostId(Integer postId) {

		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postid",postId));
		List<Comment> comments = this.commentRepo.findByPost(post);
		List<CommentDTO> commentDTOs = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDTO.class))
				.collect(Collectors.toList());

		return commentDTOs;
	}
}
