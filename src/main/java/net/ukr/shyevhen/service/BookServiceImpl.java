package net.ukr.shyevhen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Transactional(readOnly=true)
	public List<Book> findAllOrderByAddDate() {
		return bookRepository.findAllOrderByAddDateASC();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Book> findByGenreOrderByAddDate(String genre) {
		return bookRepository.findByGenreOrderByAddDate(genre);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Book> findByGenreOrderByPop(String genre) {
		return bookRepository.findByGenreOrderByPop(genre);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Book> findByGenreOrderByPrice(String genre, boolean ascending) {
		if(ascending) {
			return bookRepository.findByGenreOrderByPriceAsc(genre);
		}else {
			return bookRepository.findByGenreOrderByPriceDesc(genre);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Book> findByGenreOrderByName(String genre, boolean ascending) {
		if(ascending) {
			return bookRepository.findByGenreOrderByNameAsc(genre);
		}else {
			return bookRepository.findByGenreOrderByNameDesc(genre);
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public Book getBookById(long id) {
		return bookRepository.getBookById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Book getBookByName(String name) {
		return bookRepository.getBookByName(name);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean existBook(String name, List<Author> authors, int imprintDate, int pages, BookCover bookCover,
			String language) {
		return bookRepository.existBook(name,/* authors,*/ imprintDate, pages, bookCover, language);
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
//		bookRepository.deleteById(id);
	}

}
