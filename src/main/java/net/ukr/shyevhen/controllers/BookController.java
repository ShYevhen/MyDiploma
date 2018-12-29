package net.ukr.shyevhen.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
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

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.Genre;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.AuthorService;
import net.ukr.shyevhen.service.BasketService;
import net.ukr.shyevhen.service.BookService;
import net.ukr.shyevhen.service.GenreService;
import net.ukr.shyevhen.service.UserService;

@Controller
@RequestMapping("/books")
public class BookController {
	static final int pageItems = 12;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private BasketService basketService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private AuthorService authorService;

	// Получение списка книг
	@GetMapping(value = "/")
	public String getBooks(@CookieValue("JSESSIONID") String cookie, Model model) {
		String visible = checkLoginAndBasket(cookie, model);
		List<Book> books = bookService.findByGenre("", PageRequest.of(0, pageItems, Sort.Direction.DESC, "addDate"),
				visible);
		model.addAttribute("books", books);
		return "books";
	}

	// Получение полной информации о книге
	@GetMapping(value = "")
	public String getBookById(@RequestParam Long id, @CookieValue("JSESSIONID") String cookie, Model model) {
		String role = checkLoginAndBasket(cookie, model);
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		String path = book.getGenre().getPath();
		List<Genre> genres = new ArrayList<>();
		int num = path.split("/").length - 3;
		for (int i = 0; i < num; i++) {
			path = path.substring(0, path.lastIndexOf("/"));
			genres.add(genreService.getGenreByPath(path));
		}
		sortGenre(genres);
		if (role.equals("ADMIN") || role.equals("OPERATOR")) {
			Pageable pageable = PageRequest.of(0, authorService.countAuthors(),
					Sort.by(Sort.Order.asc("surname"), Sort.Order.asc("name")));
			model.addAttribute("authorsList", authorService.getAuthors(pageable));
			addGenres(model);
		}
		model.addAttribute("genres", genres);
		return "book";
	}

	// Изменение информации о книге
	@PostMapping(value = "/update")
	public ResponseEntity<String> updateBookById(@RequestBody Book book) {
		if (!bookService.existBook(book.getId())) {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		}
		Book bookDB = bookService.getBookById(book.getId());
		if (book.getName() != null) {
			bookDB.setName(book.getName());
		}
		if (book.getAuthors().size() > 0) {
			bookDB.setAuthors(book.getAuthors());
		}

		if (book.getGenre() != null) {
			bookDB.setGenre(book.getGenre());
		}
		if (book.getBookSeries() != null) {
			bookDB.setBookSeries(book.getBookSeries());
		}
		if (book.getBookInSeries() != -1) {
			bookDB.setBookInSeries(book.getBookInSeries());
		}
		if (book.getImprintDate() != -1) {
			bookDB.setImprintDate(book.getImprintDate());
		}
		changeBook(bookDB, book);
		bookDB.setAddDate(new Date());
		bookService.changeBookData(bookDB);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	private void changeBook(Book bookDB, Book book) {
		if (book.getPages() != -1) {
			if (book.getPages() > 0) {
				bookDB.setPages(book.getPages());
			} else {
				bookDB.setBookSeries(null);
				bookDB.setBookInSeries(0);
			}
		}
		if (book.getBookCover() != null) {
			bookDB.setBookCover(book.getBookCover());
		}
		if (book.getLanguage() != null) {
			bookDB.setLanguage(book.getLanguage());
		}
		if (book.getPrice() != null) {
			bookDB.setPrice(book.getPrice());
		}
		if (book.getAvailability() != -1) {
			bookDB.setAvailability(book.getAvailability());
		}
		bookDB.setVisible(book.isVisible());
		if (book.getDescription() != null) {
			bookDB.setDescription(book.getDescription());
		}
		if (book.getImage() != null) {
			bookDB.setImage(saveImage(book.getImage(), bookDB.getName() + "." + bookDB.getImprintDate()));
		}
	}

	@GetMapping(value = "/add")
	public String addBookPage(@CookieValue("JSESSIONID") String cookie, Model model) {
		checkLoginAndBasket(cookie, model);
		model.addAttribute("newBook", true);
		Pageable pageable = PageRequest.of(0, authorService.countAuthors(),
				Sort.by(Sort.Order.asc("surname"), Sort.Order.asc("name")));
		model.addAttribute("authorsList", authorService.getAuthors(pageable));
		addGenres(model);
		return "book";
	}

	// Добавление новой книги
	@PostMapping(value = "/add")
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		if (bookService.existBook(book.getName(), book.getAuthors(), book.getImprintDate(), book.getPages(),
				book.getBookCover(), book.getLanguage())) {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		}
		book.setImage(saveImage(book.getImage(), book.getName() + "." + book.getImprintDate()));
		book.setGenre(genreService.getGenreByPath(book.getGenre().getPath()));
		book.setAddDate(new Date());
		bookService.addBook(book);
		return new ResponseEntity<String>(book.getId() + "", HttpStatus.ACCEPTED);
	}

	// Получение списка книг определенного жанра
	@GetMapping(value = "/{firstG}")
	public String getBooksByGenre(@CookieValue("JSESSIONID") String cookie, Model model, @PathVariable String firstG,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "addDate") String order,
			@RequestParam(required = false, defaultValue = "null") String sortS) {
		String visible = checkLoginAndBasket(cookie, model);
		Sort.Direction sort = null;
		if (sortS.equals("DESC") || sortS.equals("null")) {
			sort = Sort.Direction.DESC;
		} else {
			sort = Sort.Direction.ASC;
		}
		List<Book> books = bookService.findByGenre(firstG, PageRequest.of(page, pageItems, sort, order), visible);
		model.addAttribute("books", books);
		model.addAttribute("genreLink", "/books/" + firstG);
		model.addAttribute("pageLink",
				"/books/" + firstG + "?order=" + order + (sortS.equals("null") ? "" : ("&sortS=" + sortS)));
		model.addAttribute("thisPage", page);
		model.addAttribute("maxPage", countPage(firstG, visible));
		return "books";
	}

	@GetMapping(value = "/{firstG}/{secondG}")
	public String getBooksByGenre(@CookieValue("JSESSIONID") String cookie, Model model, @PathVariable String firstG,
			@PathVariable String secondG, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "addDate") String order,
			@RequestParam(required = false, defaultValue = "null") String sortS) {
		String visible = checkLoginAndBasket(cookie, model);
		Sort.Direction sort = null;
		if (sortS.equals("DESC") || sortS.equals("null")) {
			sort = Sort.Direction.DESC;
		} else {
			sort = Sort.Direction.ASC;
		}
		String genre = firstG + "/" + secondG;
		List<Book> books = bookService.findByGenre(genre, PageRequest.of(page, pageItems, sort, order), visible);
		model.addAttribute("books", books);
		model.addAttribute("genreLink", "/books/" + genre);
		model.addAttribute("pageLink",
				"/books/" + genre + "?order=" + order + (sortS.equals("null") ? "" : ("&sortS=" + sortS)));
		model.addAttribute("thisPage", page);
		model.addAttribute("maxPage", countPage(genre, visible));
		return "books";
	}

	@GetMapping(value = "/{firstG}/{secondG}/{thirdG}")
	public String getBooksByGenre(@CookieValue("JSESSIONID") String cookie, Model model, @PathVariable String firstG,
			@PathVariable String secondG, @PathVariable String thirdG,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "addDate") String order,
			@RequestParam(required = false, defaultValue = "null") String sortS) {
		String visible = checkLoginAndBasket(cookie, model);
		Sort.Direction sort = null;
		if (sortS.equals("DESC") || sortS.equals("null")) {
			sort = Sort.Direction.DESC;
		} else {
			sort = Sort.Direction.ASC;
		}
		String genre = firstG + "/" + secondG + "/" + thirdG;
		List<Book> books = bookService.findByGenre(genre, PageRequest.of(page, pageItems, sort, order), visible);
		model.addAttribute("books", books);
		model.addAttribute("genreLink", "/books/" + genre);
		model.addAttribute("pageLink",
				"/books/" + genre + "?order=" + order + (sortS.equals("null") ? "" : ("&sortS=" + sortS)));
		model.addAttribute("thisPage", page);
		model.addAttribute("maxPage", countPage(genre, visible));
		return "books";
	}

	@GetMapping(value = "/{firstG}/{secondG}/{thirdG}/{fourthG}")
	public String getBooksByGenre(@CookieValue("JSESSIONID") String cookie, Model model, @PathVariable String firstG,
			@PathVariable String secondG, @PathVariable String thirdG, @PathVariable String fourthG,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "addDate") String order,
			@RequestParam(required = false, defaultValue = "null") String sortS) {
		String visible = checkLoginAndBasket(cookie, model);
		Sort.Direction sort = null;
		if (sortS.equals("DESC") || sortS.equals("null")) {
			sort = Sort.Direction.DESC;
		} else {
			sort = Sort.Direction.ASC;
		}
		String genre = firstG + "/" + secondG + "/" + thirdG + "/" + fourthG;
		List<Book> books = bookService.findByGenre(genre, PageRequest.of(page, pageItems, sort, order), visible);
		model.addAttribute("books", books);
		model.addAttribute("genreLink", "/books/" + genre);
		model.addAttribute("pageLink",
				"/books/" + genre + "?order=" + order + (sortS.equals("null") ? "" : ("&sortS=" + sortS)));
		model.addAttribute("thisPage", page);
		model.addAttribute("maxPage", countPage(genre, visible));
		return "books";
	}

	private String checkLoginAndBasket(@CookieValue("JSESSIONID") String cookie, Model model) {
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
				return sUser.getRole().toString();
			}
		} else {
			if (basketService.countByBookId(cookie) > 0) {
				model.addAttribute("basket", "full");
			} else {
				model.addAttribute("basket", "empty");
			}
		}
		return "";
	}

	private long countPage(String link, String visible) {
		long items = bookService.countByGenre("/books/" + link, visible);
		return (items / pageItems) + ((items % pageItems > 0) ? 1 : 0);
	}

	private String saveImage(String baseImg, String book) {
		String format = "";
		if (baseImg.contains("image/jpeg")) {
			format = ".jpg";
		} else if (baseImg.contains("image/png")) {
			format = ".png";
		} else if (baseImg.contains("image/gif")) {
			format = ".gif";
		}
		baseImg = baseImg.substring(baseImg.indexOf(",") + 1);
		String imgRef = "src/main/webapp/WEB-INF/static/img/books/" + book + format;
		byte[] buf = Base64.getDecoder().decode(baseImg);
		File img = new File(imgRef);
		try (OutputStream os = new FileOutputStream(img)) {
			os.write(buf);
			return imgRef.substring(imgRef.indexOf("/img"));
		} catch (IOException e) {
			System.err.println(e);
			return "/img/books/default.png";
		}
	}

	private void addGenres(Model model) {
		List<Genre> genres = genreService.findGenreByPathExept("/books/business-economics-legal");
		model.addAttribute("genresBEL", genres);
		genres = genreService.findGenreByPathExept("/books/children");
		model.addAttribute("genresCh", genres);
		genres = genreService.findGenreByPathExept("/books/documentary");
		model.addAttribute("genresDoc", genres);
		genres = genreService.findGenreByPathExept("/books/house-lifestyle-hobby");
		model.addAttribute("genresHLH", genres);
		genres = genreService.findGenreByPathExept("/books/art-culture-religion");
		model.addAttribute("genresACR", genres);
		genres = genreService.findGenreByPathExept("/books/computer");
		model.addAttribute("genresComp", genres);
		genres = genreService.findGenreByPathExept("/books/science-technology-medicine/humanities-social-sciences");
		model.addAttribute("genresHSS", genres);
		genres = genreService.findGenreByPathExepts("/books/science-technology-medicine/applied-natural-sciences",
				"/books/science-technology-medicine/applied-natural-sciences/transport");
		model.addAttribute("genresANS", genres);
		genres = genreService
				.findGenreByPathExept("/books/science-technology-medicine/applied-natural-sciences/transport");
		model.addAttribute("genresTran", genres);
		genres = genreService.findGenreByPathExept("/books/educational-reference");
		model.addAttribute("genresER", genres);
		genres = genreService.findGenreByPathExept("/books/fiction");
		model.addAttribute("genresFic", genres);
	}

	private void sortGenre(List<Genre> genres) {
		genres.sort(new Comparator<Genre>() {
			@Override
			public int compare(Genre g1, Genre g2) {
				if (g1.getPath().length() > g2.getPath().length()) {
					return 1;
				} else if (g1.getPath().length() < g2.getPath().length()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}
}