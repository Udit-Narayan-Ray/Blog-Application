package com.blog.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDTO;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {

		Category cat = this.modelMapper.map(categoryDTO, Category.class);
		Category savedCategory = this.categoryRepo.save(cat);
		CategoryDTO savedCategoryDTO = this.modelMapper.map(savedCategory, CategoryDTO.class);

		return savedCategoryDTO;
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		cat.setTitle(categoryDTO.getTitle());
		cat.setDescription(categoryDTO.getDescription());
		Category savedCategory = this.categoryRepo.save(cat);

		return this.modelMapper.map(savedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategoryById(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		return this.modelMapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {

		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDTO> categoryDTOs = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDTO.class))
				.collect(Collectors.toList());

		return categoryDTOs;
	}

	@Override
	public void deleteCategoryById(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		this.categoryRepo.delete(cat);
	}

}
