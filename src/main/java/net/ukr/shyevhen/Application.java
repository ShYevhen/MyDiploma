package net.ukr.shyevhen;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.ukr.shyevhen.controllers.BasketCleaner;
import net.ukr.shyevhen.model.Author;
import net.ukr.shyevhen.model.Basket;
import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.BookCover;
import net.ukr.shyevhen.model.Order;
import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.AuthorService;
import net.ukr.shyevhen.service.BasketService;
import net.ukr.shyevhen.service.BookService;
import net.ukr.shyevhen.service.OrderService;
import net.ukr.shyevhen.service.UserService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner test(final UserService userService, final AuthorService authorService, final BookService bookService, 
			final OrderService orderService, final BasketService basketService) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				checkAuthors();
				checkBooks();
				checkUsers();
				checkOrders();
				checkBasket();
//				ShopUser admin = new ShopUser("Admin", "admin", "Yevhen", "Shvorak", "my_email@mail.ua", "+380973183232", Role.ADMIN);	
//				userService.addUser(admin);
//				Author author = new Author("AuthorName", "Vat", new Date(), "hfkljhgkdjhglhjdkhdlkhjd");
//				authorService.addAuthor(author);
//				List<Author> listA = new ArrayList<>();
//				Author authorL = authorService.getAuthorByNameAndSurname(author.getName(), author.getSurname());
//				listA.add(authorL);
//				System.out.println(authorL.toString());
//				Book book = new Book("Red and Black", "null", 1900, 352, BookCover.HARD, "Ru", "Что тут такое?", "$ego", new BigDecimal(100), 10/*, listA*/);
//				authorL.getBooks().add(book);	
//				bookService.addBook(book);
//				authorService.changeAuthorData(authorL);
//	
//				Book ordBook = bookService.getBookByName(book.getName());
//				List<Book> listB = new ArrayList<>();
//				listB.add(ordBook);
//				ShopUser user = userService.getByUserLogin(admin.getLogin());
//				Order order = new Order(user.getName(), user.getAddress(), user.getPhone(), "Inglezi", new BigDecimal(120), new BigDecimal(120.5), user, listB);
//				orderService.addOrder(order);
//				user.getFavorite().add(ordBook);
//				userService.changeUserData(user);
//				orderService.deleteOrder(order.getId());
//				System.out.println();
//				System.out.println("UserAdmin: "+ user.toString());
//				System.out.println();
//				System.out.println("Author info: "+authorService.getAuthor(1));
//				System.out.println();
//				System.out.println("Book info: "+ordBook);
//				System.out.println();
//				System.out.println("Order info: "+orderService.getOrder(1));
				
			}
			
			private void checkAuthors() {
				Author author = new Author("AuthorName", "Wat", new Date(), "4");
				authorService.addAuthor(author);
				author = new Author("ButhorName", "Aat", new Date(), "1");
				authorService.addAuthor(author);
				author = new Author("FuthorName", "Kat", new Date(), "3");
				authorService.addAuthor(author);
				author = new Author("AuthorName", "Kat", new Date(), "2");
				authorService.addAuthor(author);
//				List<Author> listA = authorService.getAuthors();
//				for (Author author2 : listA) {
//					System.out.println(author2.getId()+" | "+author2.getSurname()+" | "+author2.getName()+" | "+author2.getBiography());
//				}
			}
			
			private void checkBooks() {
				Author author = authorService.getAuthors().get(0);
				Book book = new Book("FirstBook", null, 1992, 412, BookCover.HARD, "English", "This is book for second author", "MyGenre", new BigDecimal(120), 12);
				book.setBookSeries("First");
				book.setBookInSeries(3);
				book.addAuthor(author);
				book.setAddDate(new Date());
				book.setVisible(true);
				bookService.addBook(book);
				book = new Book("SecondBook", null, 1992, 412, BookCover.HARD, "English", "This is book for second author", "Your genre", new BigDecimal(120), 12);
				book.setBookSeries("First");
				book.setBookInSeries(2);
				book.addAuthor(author);
				book.setAddDate(new Date());
				bookService.addBook(book);
				book = new Book("ThirdBook", null, 1992, 412, BookCover.HARD, "English", "This is book for second author", "It-Genres", new BigDecimal(120), 12);
				book.setBookSeries("First");
				book.setBookInSeries(1);
				book.addAuthor(author);
				book.setVisible(true);
				book.setAddDate(new Date(156L));
				bookService.addBook(book);
//				List<Book> books = bookService.findAllOrderByAddDate();
//				for (Book book2 : books) {
//					System.out.println(book2);
//				}
//				System.out.println(bookService.existBook(book.getName(), book.getAuthors(), book.getImprintDate(), book.getPages(), book.getBookCover(), book.getLanguage()));
//				books = authorService.getAuthorById(author.getId()).getBooks();
//				for (Book book2 : books) {
//					System.out.println(book2);
//				}

			}
			
			private void checkUsers() {
				BCryptPasswordEncoder e = new BCryptPasswordEncoder();
				String pass = e.encode("password");
				ShopUser admin = new ShopUser("Admin", pass, "Yevhen", "Shvorak", "my_email@mail.ua", "+380973183232", Role.ADMIN);	
				userService.addUser(admin);
				admin = new ShopUser("OperatorOne", pass, "Name", "Surname", "my_operator@mail.ua", "+380973183232", Role.OPERATOR);	
				userService.addUser(admin);
				admin = new ShopUser("UserOne", pass, "Name", "Surname", "my_user@mail.ua", "+380973183232", Role.USER);	
				List<Book> books = bookService.findAllOrderByAddDate();
				for (Book book : books) {
					admin.addInFavorite(book);
				}
				userService.addUser(admin);
//				System.out.println(userService.existByUserLogin("Addmin"));
//				List<ShopUser> users = userService.findUsersByRole(Role.ADMIN);
//				for (ShopUser shopUser : users) {
//					System.out.println(shopUser);
//				}
//				
//				users = userService.findAllOrderByLoginASC();
//				for (ShopUser shopUser : users) {
//					System.out.println(shopUser);
//				}
//				System.out.println();
//				books = userService.getUserFavoriteList(admin.getId()).getFavorite();
//				for (Book book : books) {
//					System.out.println(book);
//				}
				
			}
			
			private void checkOrders() {
				ShopUser user = userService.findUsersByRole(Role.USER).get(0);
				List<Book> books = bookService.findByGenreOrderByName("it", true);
				Order order = new Order(user.getName(), user.getSurname(), user.getPhone(), user.getAddress(), new BigDecimal(120), new BigDecimal(125), user, books);
				orderService.addOrder(order);
				
				books = bookService.findByGenreOrderByAddDate("it");
				order = new Order(user.getName(), user.getSurname(), user.getPhone(), user.getAddress(), new BigDecimal(120), new BigDecimal(125), user, books);
				orderService.addOrder(order);
				long id = user.getId();
				user = userService.findUsersByRole(Role.OPERATOR).get(0);
				books = bookService.findByGenreOrderByAddDate("genre");
				order = new Order(user.getName(), user.getSurname(), user.getPhone(), user.getAddress(), new BigDecimal(120), new BigDecimal(125), user, books);
				orderService.addOrder(order);
				List<Order> orders = orderService.findByUserLogin(user.getLogin());
//				for (Order order2 : orders) {
//					System.out.println(order2);
//				}
				orders = orderService.findByUserId(id);
//				for (Order order2 : orders) {
//					System.out.println(order2);
//				}
				order = orderService.getOrder(orders.get(0).getId());
//				System.out.println(orderService.existOrder(order.getName(), order.getSurname(), order.getCreateDate(), order.getBooksCount(),order.getTotalPrice()));
//				System.out.println(order);
				
				
			}
			
			private void checkBasket() {
				BasketCleaner bc = new BasketCleaner(basketService);
				Book book = bookService.findAllOrderByAddDate().get(0);
				Basket basket = new Basket("session", book);
				basketService.addToBasket(basket);
//				System.out.println(basketService.countBySession("session"));
//				try {
//					TimeUnit.SECONDS.sleep(30);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				basket = new Basket("session", book);
				basketService.addToBasket(basket);
//				System.out.println(basketService.countBySession("session"));
//				basketService.deleteBySession("session");
//				for(;;) {
//					try {
//						TimeUnit.SECONDS.sleep(30);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				System.out.println(basketService.countBySession("session"));
//				
//				}
			}
		};
	}
}
