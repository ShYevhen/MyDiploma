package net.ukr.shyevhen.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.Order;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.BasketService;
import net.ukr.shyevhen.service.BookService;
import net.ukr.shyevhen.service.OrderService;
import net.ukr.shyevhen.service.UserService;

@RestController
//@Controller
public class MainController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BasketService basketService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value="/")
//	@ResponseBody
	public List<Book> getIndexPage(Model model) {
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String login = user.getUsername();
//		ShopUser sUser = userService.getByUserLogin(login);
//		if(sUser!=null) {
//			model.addAttribute("user", login);
//		}
		return bookService.findAllOrderByAddDate();
	}
	
	@GetMapping(value="/registration")
	public String getRegistrationPage() {
		return "registration";
	}
	
	@PostMapping(value="/registration")
	public String addNewUser(@RequestBody ShopUser sUser) {
		if(userService.existByUserLogin(sUser.getLogin())) {
			return "registration";
		}
		userService.addUser(sUser);
		return "index";
	}
	
//	@GetMapping(value="/login")
//	public String getLoginPage() {
//		return "";
//	}
//	
//	@PostMapping(value="/login")
//	public String getLogin(@RequestBody ShopUser sUser) {
//		
//		return "";
//	}
//	@ResponseBody
	@GetMapping(value="/basket")
	public List<Book> getBasket(@CookieValue("JSESSIONID") String cookie) {
		return basketService.findBySession(cookie);
	}
	
	@DeleteMapping(value="/basket/{id}")
	public ModelAndView deleteFromBasket(@RequestParam("id") long id) {
		basketService.deleteById(id);
		return new ModelAndView("redirect:/basket");
	}
//	@ResponseBody
	@GetMapping(value="/basket/order")
	public Order getOrderForm(@CookieValue("JSESSIONID") String cookie, Model model) {
		List<Book> books = basketService.findBySession(cookie);
		if(books.size()<1) {
			return null;
		}
		BigDecimal deliveryPrice = new BigDecimal(0);
		for (Book book : books) {
			deliveryPrice = deliveryPrice.add(book.getPrice()); 
		}
		Order order = new Order();
		order.setBooks(books);
		order.setBooksCount(books.size());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null) {
			String login = user.getUsername();
			ShopUser sUser = userService.getByUserLogin(login);
			order.setUser(sUser);
		}
		return order;
	}
	
	@PostMapping(value="/basket/order")
	public ModelAndView addOrder(@RequestBody Order order) {
		if(!orderService.existOrder(order.getName(), order.getSurname(), order.getCreateDate(), order.getBooksCount(), order.getTotalPrice())) {
			orderService.addOrder(order);
		}
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value="/search")
	public String searchBook() {
		return "";
	}
}
