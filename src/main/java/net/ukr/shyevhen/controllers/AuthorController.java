package net.ukr.shyevhen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/")
	public List<Author> getAuthors() {
		List<Author> authors = authorService.getAuthors();
		return authors;
	}
	
	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ModelAndView addAuthor(@RequestBody Author author) {
		if(!authorService.existAuthor(author.getName(), author.getSurname(), author.getBirthday())) {
			authorService.addAuthor(author);
		}
		return new ModelAndView("redirect: /author/"+author.getId());
	}
	
	@PostMapping(value = "/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void changeAuthorData(@RequestBody Author author) {
		if(authorService.existAuthor(author.getName(), author.getSurname(), author.getBirthday())) {
			authorService.changeAuthorData(author);
		}
	}
	
	@GetMapping(value = "/{id}")
	public Author getAuthor(@PathVariable Long id) {
		Author author = authorService.getAuthorById(id);
		return author;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void deleteAuthors(@PathVariable Long id) {
		authorService.deleteAuthor(id);
	}
}
