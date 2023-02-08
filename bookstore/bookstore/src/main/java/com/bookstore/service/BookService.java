package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.book;
import com.bookstore.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bRepo;
	public void save(book b) {
		bRepo.save(b);
		}
	public List<book> getAllBook(){
		return bRepo.findAll();
	}	
	public book getBookById(int id) {
		return bRepo.findById(id).get();
	}
	public void deleteById(int id) {
		bRepo.deleteById(id);
	}
}
