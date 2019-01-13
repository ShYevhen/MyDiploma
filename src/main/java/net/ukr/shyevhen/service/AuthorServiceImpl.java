package net.ukr.shyevhen.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Author> getAuthors(Pageable pageable) {
		return authorRepository.findAllAuthors(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Author> findAuthorByNameSurname(String name, String surname) {
		return authorRepository.findByNameAndSurname(name, surname);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Author> findAuthorBySurname(String surname) {
		return authorRepository.findBySurname(surname);
	}

	@Override
	@Transactional(readOnly = true)
	public int countAuthors() {
		return authorRepository.countAuthors();
	}

	@Override
	@Transactional(readOnly = true)
	public int countAuthors(String letter) {
		return authorRepository.countAuthorsByLetter(letter);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Author> getAuthorsByLetter(String letter, Pageable pageable) {
		return authorRepository.findAllBySurnameLetter(letter, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Author getAuthorByNameAndSurname(String name, String surname) {
		return authorRepository.getAuthorByNameAndSurname(name, surname);
	}

	@Override
	@Transactional(readOnly = true)
	public Author getAuthorById(long id) {
		return authorRepository.getAuthorById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existAuthor(String name, String surname, Date birthday) {
		return authorRepository.existAuthor(name, surname, birthday);
	}

	@Override
	@Transactional
	public void addAuthor(Author author) {
		authorRepository.save(author);
	}

	@Override
	@Transactional
	public void changeAuthorData(Author author) {
		authorRepository.save(author);
	}

	@Override
	@Transactional
	public void deleteAuthor(long id) {
		authorRepository.deleteById(id);
	}

}
