package com.brainmatics.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainmatics.entity.Category;
import com.brainmatics.repositories.CategoryRepo;

@Service("categoryService")
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	public Category save(Category category) {
		return categoryRepo.save(category);
	}
	
//	public Category findById(Long id) {
//		return categoryRepo.findByCategoryId(id);
//	}
	
	public Iterable<Category> findAll(){
		return categoryRepo.findAll();
	}
	
	
}
