package com.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	private String title;
	
	private String description;
	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts=new ArrayList<>();
	
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(Integer categoryId, String title, String description) {
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
	

//	public List<Post> getPosts() {
//		return posts;
//	}
//
//	public void setPosts(List<Post> posts) {
//		this.posts = posts;
//	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", title=" + title + ", description=" + description + "]";
	}
}
