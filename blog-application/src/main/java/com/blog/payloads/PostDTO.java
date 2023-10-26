package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class PostDTO {
	private Integer PostId;

	@NotEmpty(message = "!! title can not be empty !!")
	@Size(min = 5, message = "!! title should be of atleast 5 characters !!")
	private String title;

	@NotEmpty(message = "!! content can not be empty !!")
	@Size(min = 10, message = "!! minimum 10 characters description !!")
	private String content;

	private String imageName;

	private Date postedDate;

	private UserDTO user;

	private CategoryDTO category;
	
	private Set<CommentDTO> comments=new HashSet<>();

	public PostDTO() {
		// TODO Auto-generated constructor stub
	}

	public PostDTO(Integer postId,
			@NotEmpty(message = "!! title can not be empty !!") @Size(min = 5, message = "!! title should be of atleast 5 characters !!") String title,
			@NotEmpty(message = "!! content can not be empty !!") @Size(min = 10, message = "!! minimum 10 characters description !!") String content,
			String imageName, Date postedDate) {
		super();
		PostId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.postedDate = postedDate;
	}

	public Integer getPostId() {
		return PostId;
	}

	public void setPostId(Integer postId) {
		PostId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}


	public Set<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDTO> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "PostDTO [PostId=" + PostId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", postedDate=" + postedDate + ", user=" + user + ", category=" + category + "]";
	}

}
