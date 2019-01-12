package net.ukr.shyevhen.controllers;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.Order;
import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.BasketService;
import net.ukr.shyevhen.service.BookService;
import net.ukr.shyevhen.service.OrderService;
import net.ukr.shyevhen.service.UserService;

@Controller
@RequestMapping("/profile")
public class UserController {
	static final int orderItems = 30;

	@Autowired
	private UserService userService;
	@Autowired
	private BasketService basketService;
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderService orderService;

	// Главная страница профиля пользователя со всей контактной информацией
	@GetMapping(value = "/user")
	public String userPage(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		ShopUser shopUser = userService.getByUserLogin(login);
		checkLoginAndBasket(model, login);
		model.addAttribute("profile", shopUser);
		return "profile";
	}

	// Изменение информации о пользователе
	@PostMapping(value = "/user")
	public ResponseEntity<String> userUpdate(@RequestBody ShopUser userSh) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		ShopUser sUser = userService.getByUserLogin(login);
		if (!encoder.matches(userSh.getLogin(), sUser.getPassword())) {
			return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
		}
		if (!userSh.getPassword().equals("null")) {
			sUser.setPassword(encoder.encode(userSh.getPassword()));
		}
		if (!userSh.getName().equals("null")) {
			sUser.setName(userSh.getName());
		}
		if (!userSh.getSurname().equals("null")) {
			sUser.setSurname(userSh.getSurname());
		}
		if (!userSh.getPhone().equals("null")) {
			sUser.setPhone(userSh.getPhone());
		}
		if (!userSh.geteMail().equals("null")) {
			sUser.seteMail(userSh.geteMail());
		}
		if (!userSh.getImage().equals("null")) {
			sUser.setImage(userSh.getImage());
		}
		userService.changeUserData(sUser);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}

	// Получение списка книг добавленных в избранное
	@GetMapping(value = "/favorite")
	public String userFavorite(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "addDate") String order,
			@RequestParam(required = false, defaultValue = "DESC") String sortS) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		checkLoginAndBasket(model, login);
		sortFavoriteBook(model, login, sortS, order, page);
		return "profile";
	}

	// Добавление книги в избранное
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/favorite/add")
	public void addFavorite(@RequestParam long id, @CookieValue("JSESSIONID") String cookie, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		ShopUser shopUser = userService.getByUserLogin(login);
		Book book = bookService.getBookById(id);
		book.setPopulary(book.getPopulary() + 1);
		shopUser.addInFavorite(book);
		userService.changeUserData(shopUser);
	}

	// Удаление книги из избранного
	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping(value = "/favorite")
	public void userFavoriteDelete(@RequestParam("id") long bookId) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		ShopUser sUser = userService.getUserFavoriteList(login);
		sUser.deleteFromFavorite(bookId);
		userService.changeUserData(sUser);
	}

	// Получение списка заказов
	@GetMapping(value = "/orders")
	public String getOrders(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "0") long id) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		checkLoginAndBasket(model, login);
		ShopUser sUser = userService.getIdAndRoleByLogin(login);
		Sort sort = Sort.by(Sort.Order.asc("status"), Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, orderItems, sort);

		if (id > 0) {
			model.addAttribute("order", orderService.getOrder(id));
		} else if (sUser.getRole().equals(Role.USER)) {
			List<Order> orders = orderService.findByUserId(sUser.getId(), pageable);
			model.addAttribute("orders", orders);
			model.addAttribute("thisPage", page);
			model.addAttribute("pageLink", "/profile/orders?page=");
			int orderCount = orderService.countOrders(login);
			model.addAttribute("maxPage", orderCount / orderItems + ((orderCount % orderItems > 0) ? 1 : 0));
		} else {
			List<Order> orders = orderService.findByStatusOrderByCreateDate(pageable);
			model.addAttribute("orders", orders);
			model.addAttribute("thisPage", page);
			model.addAttribute("pageLink", "/profile/orders?page=");
			int orderCount = orderService.countOrders();
			model.addAttribute("maxPage", orderCount / orderItems + ((orderCount % orderItems > 0) ? 1 : 0));
		}
		return "profile";
	}

	// Удаление заказа
	@DeleteMapping(value = "/orders")
	public ResponseEntity<String> userOrderDelete(@RequestParam long id) {
		if (orderService.existOrder(id)) {
			Order order = orderService.getOrder(id);
			for (Book book : order.getBooks()) {
				book.incAvailability();
			}
			orderService.deleteOrder(id);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
	}

	// Удаление книги из заказа
	@DeleteMapping(value = "/orders/update")
	public ResponseEntity<String> orderedBookChange(@RequestParam long id, @RequestParam long bookId) {
		if (orderService.existOrder(id)) {
			Order order = orderService.getOrder(id);
			List<Book> books = order.getBooks();
			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getId() == bookId) {
					books.get(i).incAvailability();
					books.remove(i);
					changeOrderCost(order);
					orderService.changeOrderData(order);
					return new ResponseEntity<String>(HttpStatus.ACCEPTED);
				}
			}
		}
		return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
	}

	@PostMapping(value = "/orders/update")
	public ResponseEntity<String> userOrderChange(@RequestBody Order orderJson) {
		if (orderService.existOrder(orderJson.getId())) {
			Order order = orderService.getOrder(orderJson.getId());
			if (orderJson.isStatus()) {
				order.setStatus(orderJson.isStatus());
				order.setDeliveryDate(new Date());
			}

			if (orderJson.getName() != null) {
				order.setName(orderJson.getName());
			}
			if (orderJson.getSurname() != null) {
				order.setSurname(orderJson.getSurname());
			}
			if (orderJson.getPhone() != null) {
				order.setPhone(orderJson.getPhone());
			}
			orderService.changeOrderData(order);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
	}

	// Получение списка администраторов и операторов
	@GetMapping(value = "/operators")
	public String operatorsList(@RequestParam(required = false, defaultValue = "0") long id, Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		checkLoginAndBasket(model, login);
		if (id == 0) {
			List<ShopUser> userList = userService.findUsersByRole(Role.ADMIN);
			userList.addAll(userService.findUsersByRole(Role.OPERATOR));
			model.addAttribute("operators", userList);
		} else {
			ShopUser sUser = userService.getById(id);
			model.addAttribute("operator", sUser);
		}
		return "profile";
	}

	// Добавнение нового оператора или администратора
	@PostMapping(value = "/operators")
	public ResponseEntity<String> addOperator(@RequestBody ShopUser sUser) {
		if (!userService.existByUserLogin(sUser.getLogin())) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			sUser.setPassword(encoder.encode(sUser.getPassword()));
			userService.addUser(sUser);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		}
	}

	// Удаление администратора или пользователя
	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping(value = "/operators")
	public void deleteOperator(@RequestParam("id") long id) {
		userService.deleteUser(id);
	}

	// Изменение прав доступа оператора или администратора
	@PostMapping(value = "/operators/update")
	public ResponseEntity<String> changeOperator(@RequestBody ShopUser sUser) {
		if (userService.existByUserLogin(sUser.getLogin())) {
			ShopUser user = userService.getByUserLogin(sUser.getLogin());
			user.setRole(sUser.getRole());
			userService.changeUserData(user);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>(HttpStatus.PRECONDITION_FAILED);
		}

	}

	private void checkLoginAndBasket(Model model, String login) {
		ShopUser sUser = userService.getIdAndRoleByLogin(login);
		if (sUser != null) {
			model.addAttribute("user", login);
			model.addAttribute("role", sUser.getRole().toString());
		}
		if (basketService.countByBookId(login) > 0) {
			model.addAttribute("basket", "full");
		} else {
			model.addAttribute("basket", "empty");
		}
	}

	private void sortFavoriteBook(Model model, String login, String sortS, String order, int page) {
		ShopUser shopUser = userService.getUserFavoriteList(login);
		List<Book> books = shopUser.getFavorite();
		switch (order) {
		case "populary":
			books.sort(sortByPopulary());
			break;
		case "price":
			books.sort(sortByPrice(sortS));
			break;
		case "name":
			books.sort(sortByName(sortS));
			break;
		default:
			books.sort(sortByAddDate());
			break;
		}
		List<Book> sortBooks = new ArrayList<>();
		int pageItems = BookController.pageItems;
		for (int i = pageItems * page; i < pageItems * (page + 1) && i < books.size(); i++) {
			sortBooks.add(books.get(i));
		}
		model.addAttribute("favorite", sortBooks);
		model.addAttribute("favoriteLink", "/profile/favorite");
		model.addAttribute("pageLink", "/profile/favorite?order=" + order + "&sortS=" + sortS);
		model.addAttribute("thisPage", page);
		model.addAttribute("maxPage", (books.size() / pageItems + (books.size() % pageItems > 0 ? 1 : 0)));
	}

	private Comparator<Book> sortByPopulary() {
		return new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				if (o1.getPopulary() > o2.getPopulary()) {
					return 1;
				} else if (o1.getPopulary() < o2.getPopulary()) {
					return -1;
				} else {
					return 0;
				}
			}
		};
	}

	private Comparator<Book> sortByPrice(String sortS) {
		if (sortS.equals("ASC")) {
			return new Comparator<Book>() {

				@Override
				public int compare(Book o1, Book o2) {
					return o1.getPrice().compareTo(o2.getPrice());
				}

			};
		} else {
			return new Comparator<Book>() {

				@Override
				public int compare(Book o1, Book o2) {
					return (o1.getPrice().compareTo(o2.getPrice())) * (-1);
				}

			};
		}
	}

	private Comparator<Book> sortByName(String sortS) {
		if (sortS.equals("ASC")) {
			return new Comparator<Book>() {

				@Override
				public int compare(Book o1, Book o2) {
					return o1.getName().compareToIgnoreCase(o2.getName());
				}

			};
		} else {
			return new Comparator<Book>() {

				@Override
				public int compare(Book o1, Book o2) {
					return (o1.getName().compareToIgnoreCase(o2.getName())) * (-1);
				}

			};
		}
	}

	private Comparator<Book> sortByAddDate() {
		return new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				return o1.getAddDate().compareTo(o2.getAddDate()) * (-1);
			}

		};
	}

//	private String saveImage(String baseImg, String login) {
//		String format = "";
//		if (baseImg.contains("image/jpeg")) {
//			format = ".jpg";
//		} else if (baseImg.contains("image/png")) {
//			format = ".png";
//		} else if (baseImg.contains("image/gif")) {
//			format = ".gif";
//		}
//		baseImg = baseImg.substring(baseImg.indexOf(",") + 1);
//		String imgRef = "src/main/webapp/WEB-INF/static/img/users/" + login + format;
//		byte[] buf = Base64.getDecoder().decode(baseImg);
//		File img = new File(imgRef);
//		try (OutputStream os = new FileOutputStream(img)) {
//			os.write(buf);
//			return imgRef.substring(imgRef.indexOf("/img"));
//		} catch (IOException e) {
//			System.err.println(e);
//			return "/img/users/default.png";
//		}
//	}

	private void changeOrderCost(Order order) {
		BigDecimal delivery = order.getDeliveryPrice();
		BigDecimal totalPrice = new BigDecimal(0);
		for (Book book : order.getBooks()) {
			totalPrice = totalPrice.add(book.getPrice());
		}
		int booksCount = order.getBooks().size();
		order.setTotalPrice(totalPrice.add(delivery));
		order.setBooksCount(booksCount);
	}
}
