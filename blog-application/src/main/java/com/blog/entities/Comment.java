package com.blog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;

	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(Integer commentId, String content, User user, Post post) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.user = user;
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content + ", user=" + user + ", post=" + post + "]";
	}
}
