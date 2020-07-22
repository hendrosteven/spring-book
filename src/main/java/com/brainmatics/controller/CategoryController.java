package com.brainmatics.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brainmatics.dto.CategoryForm;
import com.brainmatics.dto.ErrorMessage;
import com.brainmatics.entity.Category;
import com.brainmatics.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private HttpSession session;

	@GetMapping
	public String inputCategory(Model model) {
		model.addAttribute("categoryForm", new CategoryForm());
		return "category";
	}

	@PostMapping
	public String saveCategory(@Valid CategoryForm form, 
			BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			Category category = new Category();
			category.setName(form.getCategoryName());
			categoryService.save(category);
			return "redirect:/";
		}else {	
			ErrorMessage msg = new ErrorMessage();
			for(ObjectError err : bindingResult.getAllErrors()) {
				msg.getMessages().add(err.getDefaultMessage());
			}
			model.addAttribute("ERROR", msg);
			return "category";
		}
	}
}
