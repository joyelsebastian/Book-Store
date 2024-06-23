package com.bookStore.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.service.BookService;
import com.bookStore.bookStore.service.MyBookListService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private MyBookListService myBookListService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/book-register")
	public String bookRegister() {
		return "bookRegister";
	}

	@GetMapping("/available-books")
	public ModelAndView getAllBook() {
		List<Book> list = bookService.getAllBook();
		return new ModelAndView("bookList", "book", list);
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute Book book) {
		bookService.save(book);
		return "redirect:/available-books";
	}

	@GetMapping("/my-books")
	public String getMyBooks(Model model) {
		List<MyBookList> myBookList = myBookListService.getAllMyBooks();
		model.addAttribute("book", myBookList);
		return "myBooks";
	}

	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
		MyBookList myBookList = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
		myBookListService.saveMyBooks(myBookList);
		return "redirect:/my-books";
	}

	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "bookEdit";
	}

	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteById(id);
		return "redirect:/available-books";
	}
}
