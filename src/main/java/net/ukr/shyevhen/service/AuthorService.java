package net.ukr.shyevhen.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import net.ukr.shyevhen.model.Author;

public interface AuthorService {
	List<Author> getAuthors(Pageable pageable);

	List<Author> getAuthorsByLetter(String letter, Pageable pageable);

	List<Author> findAuthorByNameSurname(String name, String surname);

	List<Author> findAuthorBySurname(String surname);

	Author getAuthorByNameAndSurname(String name, String surname);

	Author getAuthorById(long id);

	int countAuthors();

	int countAuthors(String letter);

	boolean existAuthor(String name, String surname, Date birthday);

	void addAuthor(Author author);

	void changeAuthorData(Author author);

	void deleteAuthor(long id);
}
