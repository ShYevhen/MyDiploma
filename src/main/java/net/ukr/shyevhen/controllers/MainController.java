package net.ukr.shyevhen.controllers;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.model.Basket;
import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.Order;
import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.AuthorService;
import net.ukr.shyevhen.service.BasketService;
import net.ukr.shyevhen.service.BookService;
import net.ukr.shyevhen.service.OrderService;
import net.ukr.shyevhen.service.UserService;

@Controller
public class MainController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	@Autowired
	private BasketService basketService;

	@Autowired
	private OrderService orderService;

	private Comparator<Book> bookSort = new Comparator<Book>() {
		@Override
		public int compare(Book b1, Book b2) {
			return b1.getName().compareTo(b2.getName());
		}
	};

	@GetMapping(value = "/new")
	@ResponseBody
	public List<Book> getIndexPage() {
		return bookService.findTopTenOrderByAddDate();
	}

	@GetMapping(value = "/")
	public String getIndexPage(HttpServletResponse resp,
			@CookieValue(value = "JSESSIONID", required = false) String cookie, Model model) {
		if (cookie == null) {
			Random ran = new Random();
			resp.addCookie(new Cookie("JSESSIONID", "" + ran.nextInt(32)));
		}
		checkLoginAndBasket(cookie, model);
		return "index";
	}

	@GetMapping(value = "/registration")
	public String getRegistrationPage(@CookieValue("JSESSIONID") String cookie, Model model) {
		checkLoginAndBasket(cookie, model);
		return "registration";
	}

	@PostMapping(value = "/registration")
	public ResponseEntity<String> addNewUser(@RequestBody ShopUser sUser) {
		if (userService.existByUserLogin(sUser.getLogin())) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		sUser.setPassword(encoder.encode(sUser.getPassword()));
//		String baseImg = sUser.getImage();
//		if (baseImg != null) {
//			saveImage(baseImg, sUser);
//			
//		}
		sUser.setRole(Role.USER);
		userService.addUser(sUser);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
//	private void saveImage(String baseImg, ShopUser sUser) {
//		String format = "";
//		if (baseImg.contains("image/jpeg")) {
//			format = ".jpg";
//		} else if (baseImg.contains("image/png")) {
//			format = ".png";
//		} else if (baseImg.contains("image/gif")) {
//			format = ".gif";
//		}
//		baseImg = baseImg.substring(baseImg.indexOf(",") + 1);
//		String imgRef = "src/main/webapp/WEB-INF/static/img/users/" + sUser.getLogin() + format;
//		byte[] buf = Base64.getDecoder().decode(baseImg);
//		File img = new File(imgRef);
//		try (OutputStream os = new FileOutputStream(img)) {
//			os.write(buf);
//			sUser.setImage(imgRef.substring(imgRef.indexOf("/img")));
//		} catch (IOException e) {
//			System.err.println(e);
//			sUser.setImage("/img/users/default.png");
//		}
//	}

	@GetMapping(value = "/login")
	public String getLoginPage(@CookieValue("JSESSIONID") String cookie, Model model) {
		checkLoginAndBasket(cookie, model);
		return "login";
	}

	@GetMapping(value = "/basket")
	public String getBasket(@CookieValue("JSESSIONID") String cookie, Model model) {
		Basket basket = null;
		if (!checkLoginAndBasket(cookie, model).equals("")) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String login = user.getUsername();
			basket = basketService.getBySession(login);

			model.addAttribute("logUser", userService.getByUserLogin(login));
		} else {
			basket = basketService.getBySession(cookie);
		}
		if (basket != null) {
			List<Book> books = basket.getBooks();
			for (Book book : books) {
				book.setDescription(null);
				for (Author author : book.getAuthors()) {
					author.setBiography(null);
				}
			}
			books.sort(bookSort);
			model.addAttribute("books", books);
			return "basket";
		}
		return "index";
	}

	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/basket/add")
	public void addToBasket(@CookieValue("JSESSIONID") String cookie, @RequestParam Long id) {
		Book book = bookService.getBookById(id);
		Basket basket = null;
		String session = "";
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String login = user.getUsername();
			ShopUser sUser = userService.getIdAndRoleByLogin(login);
			if (sUser != null) {
				basket = basketService.getBySession(login);
				session = login;
			} else {
				basket = basketService.getBySession(cookie);
				session = cookie;
			}
		} else {
			basket = basketService.getBySession(cookie);
			session = cookie;
		}
		if (basket == null) {
			basket = new Basket(session);
		}
		book.setPopulary(book.getPopulary() + 2);
		basket.addBook(book);
		basketService.addToBasket(basket);
	}

	@DeleteMapping(value = "/basket")
	public ResponseEntity<String> deleteFromBasket(@CookieValue("JSESSIONID") String cookie,
			@RequestParam("id") long id) {
		Basket basket = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String login = user.getUsername();
			ShopUser sUser = userService.getIdAndRoleByLogin(login);
			if (sUser != null) {
				basket = basketService.getBySession(login);
			} else {
				basket = basketService.getBySession(cookie);
			}
		} else {
			basket = basketService.getBySession(cookie);
		}
		if (basket == null) {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		}
		for (int i = 0; i < basket.getBooks().size(); i++) {
			if (basket.getBooks().get(i).getId() == id) {
				basket.getBooks().remove(i);
				break;
			}
		}
		basketService.addToBasket(basket);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/basket")
	public ResponseEntity<String> addOrder(@CookieValue("JSESSIONID") String cookie, @RequestBody Order order) {
		if (!orderService.existOrder(order.getName(), order.getSurname(), order.getDeliveryPrice(),
				order.getTotalPrice())) {
			String session = "";
			if (SecurityContextHolder.getContext().getAuthentication() != null
					&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
					&& !(SecurityContextHolder.getContext()
							.getAuthentication() instanceof AnonymousAuthenticationToken)) {
				session = chooseSession(order, cookie);
			} else {
				session = cookie;
			}
			for (Book book : order.getBooks()) {
				book = bookService.getBookById(book.getId());
				if (book.getAvailability() > 0) {
					book.decAvailability();
				} else {
					return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
				}
			}
			order.setCreateDate(new Date());
			orderService.addOrder(order);
			System.out.println();
			System.out.println("basket session =" + session);
			System.out.println();
			basketService.deleteBySession(session);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
	}
	
	private String chooseSession(Order order, String cookie) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		ShopUser sUser = userService.getIdAndRoleByLogin(login);
		if (sUser != null) {
			order.setUser(sUser);
			return login;
		} else {
			return cookie;
		}
	}

	@GetMapping(value = "/search")
	public String searchBook(@CookieValue("JSESSIONID") String cookie, Model model,
			@RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "search_text", required = false) String sText,
			@RequestParam(name = "search_author", required = false) String sAuthor,
			@RequestParam(name = "search_order", required = false) String sOrder) {
		String visible = checkLoginAndBasket(cookie, model);
		if (sText != null) {
			return searchBookName(sText, model, visible, page);
		} else if (sAuthor != null) {
			return searchAuthor(sAuthor, model, page);
		} else if (sOrder != null) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String login = user.getUsername();
			return searchOrder(sOrder, visible, login, page, model);
		}
		return "";
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

	private String searchBookName(String sText, Model model, String visible, int page) {
		List<Book> books = new ArrayList<>();
		String[] splitText = sText.split(" ");
		String exept = "";
		for (int i = splitText.length - 1; i >= 0; i--) {
			String search = "";
			for (int j = 0; j <= i; j++) {
				search += splitText[j] + ((j < i) ? " " : "");
			}
			books.addAll((exept.equals("")) ? bookService.findBooksByName(search, visible)
					: bookService.findBooksByName(search, exept, visible));
			exept = search;
		}
		if (books.size() > 0) {
			onePage(books, model, page);
		}
		model.addAttribute("pageLink", "/search?search_text=" + sText);
		return "books";
	}

	private String searchAuthor(String sAuthor, Model model, int page) {
		List<Author> authors = new ArrayList<>();
		String[] splitAuthor = sAuthor.split(" ");
		if (splitAuthor.length == 1) {
			authors.addAll(authorService.findAuthorBySurname(sAuthor));
		} else if (splitAuthor.length > 1) {
			List<Author> temp = new ArrayList<>();
			for (int i = 0; i < splitAuthor.length; i++) {
				for (int j = 0; j < splitAuthor.length; j++) {
					if (i != j) {
						authors.addAll(authorService.findAuthorByNameSurname(splitAuthor[i], splitAuthor[j]));
					}
				}
				temp.addAll(authorService.findAuthorBySurname(splitAuthor[i]));
			}
			for (Author author : authors) {
				temp.remove(author);
			}
			authors.addAll(temp);
		}
		if (authors.size() > 0) {
			onePage(authors, model, page);
		}
		model.addAttribute("pageLink", "/search?search_author=" + sAuthor);
		return "authors";
	}

	private String searchOrder(String sOrder, String visible, String login, int page, Model model) {
		List<Order> orders = new ArrayList<>();
		try {
			long order = Long.parseLong(sOrder);
			searchOrders(visible, order, login, orders, sOrder);
		} catch (NumberFormatException e) {
			if (visible.equalsIgnoreCase("ROLE_ADMIN") || visible.equalsIgnoreCase("ROLE_OPERATOR")) {
				orders.addAll(orderService.findByUserLogin(sOrder));
			}
		}
		if (orders.size() > 0) {
			onePage(orders, model, page);
		} else {
			model.addAttribute("search", "t");
		}
		model.addAttribute("pageLink", "/search?search_order=" + sOrder + "&page=");
		return "profile";
	}
	
	private void searchOrders(String visible, long order, String login, List<Order> orders, String sOrder) {
		Order orderT = null;
		if (visible.equalsIgnoreCase("ROLE_ADMIN") || visible.equalsIgnoreCase("ROLE_OPERATOR")) {
			orderT = orderService.getOrder(order);
		} else {
			orderT = orderService.getOrder(login, order);
		}
		if (orderT != null)
			orders.add(orderT);
		if (visible.equalsIgnoreCase("ROLE_ADMIN") || visible.equalsIgnoreCase("ROLE_OPERATOR")) {
			orders.addAll(orderService.findByOrderId(order, order));
		} else {
			orders.addAll(orderService.findByOrderId(login, order, order));
		}
		if (visible.equalsIgnoreCase("ROLE_ADMIN") || visible.equalsIgnoreCase("ROLE_OPERATOR")) {
			orders.addAll(orderService.findByUserLogin(sOrder));
		}
	}

	private <T extends Object> void onePage(List<T> list, Model model, int page) {
		if (list.get(0).getClass() == Book.class) {
			List<Book> books = new ArrayList<>();
			int pageItems = BookController.pageItems;
			for (int i = page * pageItems; i < (page + 1) * pageItems; i++) {
				if (i > list.size() - 1) {
					break;
				}
				books.add((Book) list.get(i));
			}
			model.addAttribute("books", books);
			model.addAttribute("maxPage", (list.size() / pageItems) + ((list.size() % pageItems > 0) ? 1 : 0));
		} else if (list.get(0).getClass() == Author.class) {
			List<Author> authors = new ArrayList<>();
			int pageItems = AuthorController.authorSize;
			for (int i = page * pageItems; i < (page + 1) * pageItems; i++) {
				if (i > list.size() - 1) {
					break;
				}
				authors.add((Author) list.get(i));
			}
			model.addAttribute("authors", authors);
			model.addAttribute("maxPage", (list.size() / pageItems) + ((list.size() % pageItems > 0) ? 1 : 0));
		} else if (list.get(0).getClass() == Order.class) {
			onePageOrders(page, list, model);
		}
	}
	
	private <T extends Object> void onePageOrders(int page, List<T> list, Model model) {
		List<Order> orders = new ArrayList<>();
		int pageItems = UserController.orderItems;
		for (int i = page * pageItems; i < (page + 1) * pageItems; i++) {
			if (i > list.size() - 1) {
				break;
			}
			orders.add((Order) list.get(i));
		}
		model.addAttribute("orders", orders);
		model.addAttribute("maxPage", (list.size() / pageItems) + ((list.size() % pageItems > 0) ? 1 : 0));
	}
}
