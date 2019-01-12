package net.ukr.shyevhen.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.AuthorService;
import net.ukr.shyevhen.service.BasketService;
import net.ukr.shyevhen.service.UserService;

@Controller
@RequestMapping("/authors")
public class AuthorController {
	static final int authorSize = 60;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private UserService userService;

	@Autowired
	private BasketService basketService;

	@GetMapping("")
	public String getAuthors(@CookieValue("JSESSIONID") String cookie, Model model,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "null") String letter) {
		checkLoginAndBasket(cookie, model);
		Sort sort = Sort.by(Sort.Order.asc("surname"), Sort.Order.asc("name"));
		Pageable pageable = PageRequest.of(page, authorSize, sort);
		if (letter.equals("null")) {
			List<Author> authors = authorService.getAuthors(pageable);
			model.addAttribute("authors", authors);
			int count = authorService.countAuthors();
			model.addAttribute("maxPage", (count / authorSize + (count % authorSize > 0 ? 1 : 0)));
			model.addAttribute("pageLink", "/authors?");
		} else {
			List<Author> authors = authorService.getAuthorsByLetter(letter, pageable);
			model.addAttribute("authors", authors);
			int count = authorService.countAuthors(letter);
			model.addAttribute("maxPage", (count / authorSize + (count % authorSize > 0 ? 1 : 0)));
			model.addAttribute("pageLink", "/authors?letter=" + letter + "&");
		}
		model.addAttribute("thisPage", page);
		return "authors";
	}

	@GetMapping(value = "/add")
	public String addAuthorPage(@CookieValue("JSESSIONID") String cookie, Model model) {
		checkLoginAndBasket(cookie, model);
		model.addAttribute("newAuthor", true);
		return "author";
	}

	@PostMapping(value = "/add")
	public ResponseEntity<String> addAuthor(@RequestBody Author author) {
		if (authorService.existAuthor(author.getName(), author.getSurname(), author.getBirthdayDate())) {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		}
		author.setImage(saveImage(author.getImage(), checkName(author.getSurname() + author.getName())));
		authorService.addAuthor(author);
		return new ResponseEntity<String>(author.getId() + "", HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/author/update")
	public ResponseEntity<String> changeAuthorData(@RequestBody Author author) {
		Author authorDB = authorService.getAuthorById(author.getId());
		if (authorDB == null) {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		}
		if (author.getSurname() != null) {
			authorDB.setSurname(author.getSurname());
		}
		if (author.getName() != null) {
			authorDB.setName(author.getName());
		}
		if (author.getBirthday() != null) {
			authorDB.setBirthday(author.getBirthday());
		}
		if (author.getBiography() != null) {
			authorDB.setBiography(author.getBiography());
		}
		if (author.getImage() != null) {
			authorDB.setImage(saveImage(author.getImage(), checkName(authorDB.getSurname() + authorDB.getName())));
		}
		authorService.changeAuthorData(authorDB);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/author")
	public String getAuthor(@CookieValue("JSESSIONID") String cookie, Model model, @RequestParam long id) {
		checkLoginAndBasket(cookie, model);
		Author author = authorService.getAuthorById(id);
		model.addAttribute("author", author);
		return "author";
	}

	@DeleteMapping(value = "/author")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteAuthors(Model model, @RequestParam long id) {
		authorService.deleteAuthor(id);
	}

	private void checkLoginAndBasket(@CookieValue("JSESSIONID") String cookie, Model model) {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String login = user.getUsername();
			ShopUser sUser = userService.getIdAndRoleByLogin(login);
			if (sUser != null) {
				model.addAttribute("user", login);
				model.addAttribute("role", sUser.getRole().toString());
				if (basketService.countByBookId(login) > 0) {
					model.addAttribute("basket", "full");
				} else {
					model.addAttribute("basket", "empty");
				}
			}
		} else {
			if (basketService.countByBookId(cookie) > 0) {
				model.addAttribute("basket", "full");
			} else {
				model.addAttribute("basket", "empty");
			}
		}

	}

	private String saveImage(String baseImg, String author) {
		String format = "";
		if (baseImg.contains("image/jpeg")) {
			format = ".jpg";
		} else if (baseImg.contains("image/png")) {
			format = ".png";
		} else if (baseImg.contains("image/gif")) {
			format = ".gif";
		}
		baseImg = baseImg.substring(baseImg.indexOf(",") + 1);
		String imgRef = "/WEB-INF/static/img/authors/" + author + format;
		byte[] buf = Base64.getDecoder().decode(baseImg);
		File img = new File(imgRef);
		try (OutputStream os = new FileOutputStream(img)) {
			os.write(buf);
			return imgRef.substring(imgRef.indexOf("/img"));
		} catch (IOException e) {
			System.err.println(e);
			return "/img/authors/default.png";
		}
	}
	
	private String checkName(String name) {
		String regex = "[\\\\/:*?\"<>|]";
		return name.replaceAll(regex, "");
	}
}
