package com.brainmatics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brainmatics.dto.BookForm;
import com.brainmatics.entity.Book;
import com.brainmatics.services.BookService;
import com.brainmatics.services.CategoryService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String inputBook(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("bookForm", new BookForm());
		return "add";
	}
	
	@PostMapping
	public String saveBook(BookForm bookForm, Model model) {
		Book book = new Book();
		book.setTitle(bookForm.getTitle());
		book.setAuthor(bookForm.getAuthor());
		book.setDescriptions(bookForm.getDescription());
		book.setImage(bookForm.getImage());
		book.setCategory(categoryService.findById(bookForm.getCategoryId()).get());
		bookService.save(book);
		return "redirect:/";
	}
}
