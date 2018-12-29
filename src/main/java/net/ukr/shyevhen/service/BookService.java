package net.ukr.shyevhen.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.BookCover;

public interface BookService {

	List<Book> findTopTenOrderByAddDate();

	List<Book> findByGenre(String genre, Pageable pageable, String visible);

	List<Book> findBooksByName(String name, String visible);

	List<Book> findBooksByName(String name, String exept, String visible);

	Book getBookByName(String name);

	Book getBookById(long id);

	boolean existBook(String name, List<Author> authors, int imprintDate, int pages, BookCover bookCover,
			String language);

	boolean existBook(long id);

	void addBook(Book book);

	void changeBookData(Book book);

	void deleteBook(long id);

	long countByGenre(String genre, String visible);
}
