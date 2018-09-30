package net.ukr.shyevhen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.BookCover;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.name, b.price) FROM Book b WHERE b.availability > 0 AND"
			+ " b.visible = true ORDER BY b.addDate DESC")
	List<Book> findAllOrderByAddDateASC();
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.name, b.price) FROM Book b WHERE b.availability > 0 AND"
			+ " b.visible = true AND b.genre LIKE %:genre% ORDER BY b.addDate DESC")
	List<Book> findByGenreOrderByAddDate(@Param("genre") String genre);
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.name, b.price) FROM Book b WHERE b.availability > 0 AND"
			+ " b.visible = true AND b.genre LIKE %:genre% ORDER BY b.populary DESC")
	List<Book> findByGenreOrderByPop(@Param("genre") String genre);
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.name, b.price) FROM Book b WHERE b.availability > 0 AND"
			+ " b.visible = true AND b.genre LIKE %:genre% ORDER BY b.price DESC")
	List<Book> findByGenreOrderByPriceDesc(@Param("genre") String genre);
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.name, b.price) FROM Book b WHERE b.availability > 0 AND"
			+ " b.visible = true AND b.genre LIKE %:genre% ORDER BY b.price ASC")
	List<Book> findByGenreOrderByPriceAsc(@Param("genre") String genre);
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.name, b.price) FROM Book b WHERE b.availability > 0 AND"
			+ " b.visible = true AND b.genre LIKE %:genre% ORDER BY b.name DESC")
	List<Book> findByGenreOrderByNameDesc(@Param("genre") String genre);
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.name, b.price) FROM Book b WHERE b.availability > 0 AND"
			+ " b.visible = true AND b.genre LIKE %:genre% ORDER BY b.name ASC")
	List<Book> findByGenreOrderByNameAsc(@Param("genre") String genre);
	
	Book getBookByName(@Param("name") String name);
	
	Book getBookById(@Param("id") long id);
	
	@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.name = :name AND b.imprintDate = :imprintDate AND "
			+ "b.pages = :pages AND b.bookCover = :bookCover AND b.language = :language")// AND b.authors=:authors
	boolean existBook(@Param("name") String name,/* @Param("authors") List<Author> authors,*/ @Param("imprintDate") int imprintDate, 
			@Param("pages") int pages, @Param("bookCover") BookCover bookCover, @Param("language")	String language);
}
