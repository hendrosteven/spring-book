package com.brainmatics.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.brainmatics.entity.Book;

public interface BookRepo extends PagingAndSortingRepository<Book, Long> {

}
