package net.ukr.shyevhen.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.BookCover;

public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.image, b.name, b.price, b.availability) FROM Book b WHERE b.visible = true")
	List<Book> findTopTenOrderByAddDateASC(Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.image, b.name, b.price, b.availability) FROM Book b WHERE b.genre.path "
			+ "LIKE %:genre%")
	List<Book> findByGenre(@Param("genre") String genre, Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.image, b.name, b.price, b.availability) FROM Book b WHERE b.visible = true "
			+ "AND b.genre.path LIKE %:genre%")
	List<Book> findByGenreVisible(@Param("genre") String genre, Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.image, b.name, b.price, b.availability) FROM Book b WHERE b.visible = true "
			+ "AND b.name LIKE %:name% AND b.name NOT LIKE %:exept%")
	List<Book> findBooksByNameVisible(@Param("name") String name, @Param("exept") String exept);

	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.image, b.name, b.price, b.availability) FROM Book b WHERE b.name LIKE "
			+ "%:name% AND b.name NOT LIKE %:exept%")
	List<Book> findBooksByName(@Param("name") String name, @Param("exept") String exept);

	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.image, b.name, b.price, b.availability) FROM Book b WHERE b.visible = true "
			+ "AND b.name LIKE %:name%")
	List<Book> findBooksByNameVisible(@Param("name") String name);

	@Query("SELECT NEW net.ukr.shyevhen.model.Book(b.id, b.image, b.name, b.price, b.availability) FROM Book b WHERE b.name LIKE %:name%")
	List<Book> findBooksByName(@Param("name") String name);

	Book getBookByName(@Param("name") String name);

	Book getBookById(@Param("id") long id);

	@Query("SELECT COUNT(b) FROM Book b WHERE b.visible = true AND b.genre.path LIKE :genre%")
	long countByGenreVisible(@Param("genre") String genre);

	@Query("SELECT COUNT(b) FROM Book b WHERE b.genre.path LIKE :genre%")
	long countByGenre(@Param("genre") String genre);

	@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.name = :name AND b.imprintDate = :imprintDate AND "
			+ "b.pages = :pages AND b.bookCover = :bookCover AND b.language = :language")
	boolean existBook(@Param("name") String name, @Param("imprintDate") int imprintDate, @Param("pages") int pages,
			@Param("bookCover") BookCover bookCover, @Param("language") String language);

	@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.id = :id")
	boolean existBook(@Param("id") long id);
}
