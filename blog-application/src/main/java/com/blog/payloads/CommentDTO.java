package com.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDTO {
	private Integer commentId;
	
	@NotEmpty(message = "!! comment should not be left blank !!")
	@Size(min = 1,message = "!! Try with more letters !!")
	private String content;
	
	private UserDTO user;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentDTO() {
		// TODO Auto-generated constructor stub
	}

	public CommentDTO(Integer commentId, String content) {
		super();
		this.commentId = commentId;
		this.content = content;
	}

	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", content=" + content + "]";
	}
}
