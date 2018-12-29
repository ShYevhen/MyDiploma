package net.ukr.shyevhen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.BookCover;
import net.ukr.shyevhen.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findTopTenOrderByAddDate() {
		Pageable pageable = PageRequest.of(0, 12, Sort.Direction.DESC, "addDate");
		return bookRepository.findTopTenOrderByAddDateASC(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Book getBookById(long id) {
		return bookRepository.getBookById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Book getBookByName(String name) {
		return bookRepository.getBookByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existBook(String name, List<Author> authors, int imprintDate, int pages, BookCover bookCover,
			String language) {
		return bookRepository.existBook(name, imprintDate, pages, bookCover, language);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existBook(long id) {
		return bookRepository.existBook(id);
	}

	@Override
	@Transactional
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	@Transactional
	public void changeBookData(Book book) {
		bookRepository.save(book);
	}

	@Override
	@Transactional
	public void deleteBook(long id) {
		bookRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findByGenre(String genre, Pageable pageable, String visible) {
		if (visible.equalsIgnoreCase("ADMIN") || visible.equalsIgnoreCase("OPERATOR")) {
			return bookRepository.findByGenre(genre, pageable);
		}
		return bookRepository.findByGenreVisible(genre, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public long countByGenre(String genre, String visible) {
		if (visible.equalsIgnoreCase("ADMIN") || visible.equalsIgnoreCase("OPERATOR")) {
			return bookRepository.countByGenre(genre);
		}
		return bookRepository.countByGenreVisible(genre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findBooksByName(String name, String visible) {
		if (visible.equalsIgnoreCase("ADMIN") || visible.equalsIgnoreCase("OPERATOR")) {
			return bookRepository.findBooksByName(name);
		}
		return bookRepository.findBooksByNameVisible(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findBooksByName(String name, String exept, String visible) {
		if (visible.equalsIgnoreCase("ADMIN") || visible.equalsIgnoreCase("OPERATOR")) {
			return bookRepository.findBooksByName(name, exept);
		}
		return bookRepository.findBooksByNameVisible(name, exept);
	}

}
