package com.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDTO 
{
	private Integer categoryId;
	
	@NotEmpty
	@Size(min=5,max = 100,message = "!! Title should have between 5 to 100 charactes !!")
	private String title;
	
	@NotEmpty(message = "!! Description shouldn't be empty !!")
	@Size(min = 10,message = "!! Description should have atleast 10 characters !!")
	private String description;

	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CategoryDTO(Integer categoryId, @NotEmpty @Size(min = 5, max = 100) String title,
			@NotEmpty String description) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
	}


	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", title=" + title + ", description=" + description + "]";
	}	
}
