package com.brainmatics.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainmatics.entity.Book;
import com.brainmatics.repositories.BookRepo;

@Service("bookService")
@Transactional
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	private Book save(Book book) {
		return bookRepo.save(book);
	}
	
	private Iterable<Book> findAll(){
		return bookRepo.findAll();
	}
	
//	private Page<Book> findAll(Pageable pageable) {
//		//return bookRepo.findAll(pageable);
//	}
}
