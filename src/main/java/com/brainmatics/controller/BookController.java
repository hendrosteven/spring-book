package com.brainmatics.controller;

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

import com.brainmatics.dto.BookForm;
import com.brainmatics.dto.ErrorMessage;
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
	public String saveBook(@Valid BookForm bookForm, 
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttribute) {
		if (!bindingResult.hasErrors()) {
			Book book = new Book();
			book.setTitle(bookForm.getTitle());
			book.setAuthor(bookForm.getAuthor());
			book.setDescriptions(bookForm.getDescription());
			book.setImage(bookForm.getImage());
			book.setCategory(categoryService.findById(bookForm.getCategoryId()).get());
			bookService.save(book);
			return "redirect:/";
		}else {
			ErrorMessage msg = new ErrorMessage();
			for(ObjectError err: bindingResult.getAllErrors()) {
				msg.getMessages().add(err.getDefaultMessage());
			}
			redirectAttribute.addFlashAttribute("bookForm", bookForm);
			redirectAttribute.addFlashAttribute("ERROR", msg);
			return "redirect:/books";
		}
	}
}
