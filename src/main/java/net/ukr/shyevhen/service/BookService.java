package net.ukr.shyevhen.service;

import java.util.List;

import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.BookCover;

public interface BookService {
	
	List<Book> findAllOrderByAddDate();
	List<Book> findByGenreOrderByAddDate(String genre);
	List<Book> findByGenreOrderByPop(String genre);
	List<Book> findByGenreOrderByPrice(String genre, boolean ascending);
	List<Book> findByGenreOrderByName(String genre, boolean ascending);
	
	Book getBookByName(String name);
	Book getBookById(long id);
	boolean existBook(String name, List<Author> authors, int imprintDate, int pages, BookCover bookCover, String language);
	void addBook(Book book);
	void changeBookData(Book book);
	void deleteBook(long id);
}
