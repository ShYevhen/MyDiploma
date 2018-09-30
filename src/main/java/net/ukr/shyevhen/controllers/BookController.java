package net.ukr.shyevhen.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	// Получение списка книг
	@GetMapping(value = "/")
	public List<Book> getBooks() {
		List<Book> books = bookService.findAllOrderByAddDate();
		return books;
	}
	
	// Получение полной информации о книге
	@GetMapping(value = "/{id}")
	public Book getBookById(@PathVariable Long id) {
		Book book = bookService.getBookById(id);
		return book;
	}
	
	// Изменение информации о книге полной информации о книге
	@PostMapping(value = "/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateBookById(@RequestBody Book book) {
		if(bookService.existBook(book.getName(), book.getAuthors(), book.getImprintDate(), book.getPages(), book.getBookCover(), book.getLanguage())) {
		    bookService.changeBookData(book);
		}
	}
	// Добавление новой книги
	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ModelAndView addBook(@RequestBody Book book) {
		if(!bookService.existBook(book.getName(), book.getAuthors(), book.getImprintDate(), book.getPages(), book.getBookCover(), book.getLanguage())) {
		    bookService.changeBookData(book);
		}
		return new ModelAndView("redirect:/book/"+book.getId());
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void deleteBookById(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

	//----------------Получение списка книг определенного жанра-------------------------
	@GetMapping(value = "/{firstG}")
	public List<Book> getBooksByGenre(@PathVariable String firstG) {
		List<Book> books = bookService.findByGenreOrderByAddDate(firstG);
		return books;
	}

	@GetMapping(value = "/{firstG}?order={order}")
	public List<Book> getBooksByGenreOrderBy(@PathVariable String firstG, @PathVariable String order) {
		List<Book> books = new ArrayList<>();
		if(order.equalsIgnoreCase("pop")) {
			books = bookService.findByGenreOrderByPop(firstG);
		}else if(order.equalsIgnoreCase("addDate")) {
			books = bookService.findByGenreOrderByAddDate(firstG);
		}else if(order.equalsIgnoreCase("priceDesc")){
			books = bookService.findByGenreOrderByPrice(firstG, false);
		}else if(order.equalsIgnoreCase("priceAsc")){
			books = bookService.findByGenreOrderByPrice(firstG, true);
		}else if(order.equalsIgnoreCase("nameDesc")){
			books = bookService.findByGenreOrderByName(firstG, false);
		}else if(order.equalsIgnoreCase("nameAsc")){
			books = bookService.findByGenreOrderByName(firstG, true);
		}
		return books;
	}
	
	@GetMapping(value = "/{firstG}/{secondG}")
	public List<Book> getBooksByGenre(@PathVariable String firstG, @PathVariable String secondG) {
		List<Book> books = bookService.findByGenreOrderByAddDate(firstG+"/"+secondG);
		return books;
	}
	
	@GetMapping(value = "/{firstG}/{secondG}?order={order}")
	public List<Book> getBooksByGenreOrderBy(@PathVariable String firstG, @PathVariable String secondG, @PathVariable String order) {
		List<Book> books = new ArrayList<>();
		String genre = firstG+"/"+secondG;
		if(order.equalsIgnoreCase("pop")) {
			books = bookService.findByGenreOrderByPop(genre);
		}else if(order.equalsIgnoreCase("addDate")) {
			books = bookService.findByGenreOrderByAddDate(genre);
		}else if(order.equalsIgnoreCase("priceDesc")){
			books = bookService.findByGenreOrderByPrice(genre, false);
		}else if(order.equalsIgnoreCase("priceAsc")){
			books = bookService.findByGenreOrderByPrice(genre, true);
		}else if(order.equalsIgnoreCase("nameDesc")){
			books = bookService.findByGenreOrderByName(genre, false);
		}else if(order.equalsIgnoreCase("nameAsc")){
			books = bookService.findByGenreOrderByName(genre, true);
		}
		return books;
	}



	@GetMapping(value = "/{firstG}/{secondG}/{thirdG}")
	public List<Book> getBooksByGenre(@PathVariable String firstG, @PathVariable String secondG, @PathVariable String thirdG) {
		List<Book> books = bookService.findByGenreOrderByAddDate(firstG+"/"+secondG+"/"+thirdG);
		return books;
	}
	
	@GetMapping(value = "/{firstG}/{secondG}/{thirdG}?order={order}")
	public List<Book> getBooksByGenreOrderBy(@PathVariable String firstG, @PathVariable String secondG, @PathVariable String thirdG, @PathVariable String order) {
		List<Book> books = new ArrayList<>();
		String genre = firstG+"/"+secondG+"/"+thirdG;
		if(order.equalsIgnoreCase("pop")) {
			books = bookService.findByGenreOrderByPop(genre);
		}else if(order.equalsIgnoreCase("addDate")) {
			books = bookService.findByGenreOrderByAddDate(genre);
		}else if(order.equalsIgnoreCase("priceDesc")){
			books = bookService.findByGenreOrderByPrice(genre, false);
		}else if(order.equalsIgnoreCase("priceAsc")){
			books = bookService.findByGenreOrderByPrice(genre, true);
		}else if(order.equalsIgnoreCase("nameDesc")){
			books = bookService.findByGenreOrderByName(genre, false);
		}else if(order.equalsIgnoreCase("nameAsc")){
			books = bookService.findByGenreOrderByName(genre, true);
		}
		return books;
	}

	@GetMapping(value = "/{firstG}/{secondG}/{thirdG}/{fourthG}")
	public List<Book> getBooksByGenre(@PathVariable String firstG, @PathVariable String secondG, @PathVariable String thirdG, @PathVariable String fourthG) {
		List<Book> books = bookService.findByGenreOrderByAddDate(firstG+"/"+secondG+"/"+thirdG+"/"+fourthG);
		return books;
	}
	
	@GetMapping(value = "/{firstG}/{secondG}/{thirdG}/{fourthG}?order={order}")
	public List<Book> getBooksByGenreOrderBy(@PathVariable String firstG, @PathVariable String secondG, @PathVariable String thirdG, @PathVariable String fourthG, @PathVariable String order) {
		List<Book> books = new ArrayList<>();
		String genre = firstG+"/"+secondG+"/"+thirdG+"/"+fourthG;
		if(order.equalsIgnoreCase("pop")) {
			books = bookService.findByGenreOrderByPop(genre);
		}else if(order.equalsIgnoreCase("addDate")) {
			books = bookService.findByGenreOrderByAddDate(genre);
		}else if(order.equalsIgnoreCase("priceDesc")){
			books = bookService.findByGenreOrderByPrice(genre, false);
		}else if(order.equalsIgnoreCase("priceAsc")){
			books = bookService.findByGenreOrderByPrice(genre, true);
		}else if(order.equalsIgnoreCase("nameDesc")){
			books = bookService.findByGenreOrderByName(genre, false);
		}else if(order.equalsIgnoreCase("nameAsc")){
			books = bookService.findByGenreOrderByName(genre, true);
		}
		return books;
	}
}