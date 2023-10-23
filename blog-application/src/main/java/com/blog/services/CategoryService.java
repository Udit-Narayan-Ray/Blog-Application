package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDTO;


public interface CategoryService {

	//creating new category
	CategoryDTO createCategory(CategoryDTO categoryDTO);

	//updating existed category
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

	//retrieving single category
	CategoryDTO getCategoryById(Integer categoryId);

	//retrieving all categories
	List<CategoryDTO> getAllCategories();

	//deleting category corresponding to the given category id
	void deleteCategoryById(Integer categoryId);

}
