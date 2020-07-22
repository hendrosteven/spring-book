package com.brainmatics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brainmatics.dto.CategoryForm;
import com.brainmatics.entity.Category;
import com.brainmatics.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String inputCategory(Model model) {
		model.addAttribute("categoryForm", new CategoryForm());
		return "category";
	}

	@PostMapping
	public String saveCategory(@Valid CategoryForm form, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			Category category = new Category();
			category.setName(form.getCategoryName());
			categoryService.save(category);
			return "redirect:/";
		}else {
			System.out.println("Ada errors");
			return "category";
		}
	}
}
