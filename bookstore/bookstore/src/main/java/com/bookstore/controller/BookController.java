package com.bookstore.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookstore.entity.MyBookList;
import com.bookstore.entity.book;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookListService;


@Controller
public class BookController {
	@Autowired
	private BookService service;
	@Autowired
	private MyBookListService myBookService;
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister" ;
	}
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<book> list=service.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
//		return m;
		return new ModelAndView("booklist","book",list);
	}
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute book b) {
		service.save(b);
		return "redirect:/available_books";
	}
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		book b=service.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model){
		book b=service.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}
}
