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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDTO;
import com.blog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/post")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO createdCategory = this.categoryService.createCategory(categoryDTO);
		
		return new ResponseEntity<CategoryDTO>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping("/put/{catId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable("catId") Integer categoryId) {
		CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO, categoryId);

		return ResponseEntity.ok(updatedCategory);
	}

	@GetMapping("/get/{catId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("catId") Integer categoryId) {

		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}

	@GetMapping("/get")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {

		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}
	
	@DeleteMapping("/delete/{catId}")
	public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("catId")Integer categoryId)
	{
		this.categoryService.deleteCategoryById(categoryId);
		ApiResponse apiResponse=new ApiResponse("Category Successfully deleted with Category Id :- "+categoryId,true);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		
		//**both accepted, above and below but make sure to know that object is need to pass information
		
//		return new ResponseEntity<ApiResponse>(new ApiResponse("Category successfully deleted with Category id :- "+categoryId,true),HttpStatus.OK);
	}
}
