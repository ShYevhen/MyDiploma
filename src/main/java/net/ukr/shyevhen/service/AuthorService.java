package net.ukr.shyevhen.service;

import java.util.Date;
import java.util.List;

import net.ukr.shyevhen.model.Author;

public interface AuthorService {
	List<Author> getAuthors();
	Author getAuthorByNameAndSurname(String name, String surname);
	Author getAuthorById(long id);
	boolean existAuthor(String name, String surname, Date birthday);
	void addAuthor(Author author);
	void changeAuthorData(Author author);
	void deleteAuthor(long id);
}
