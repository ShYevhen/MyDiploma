package net.ukr.shyevhen.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String bookSeries;
	private int bookInSeries;
	private int imprintDate;
	private int pages;
	@Enumerated(EnumType.STRING)
	private BookCover bookCover;
	private String language;
	@Lob
	private String description;
	private BigDecimal price;
	private int availability;
	private String image;
	private boolean visible;
	private int populary;
	private Date addDate;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm");

	@ManyToOne
	private Genre genre;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Author> authors = new ArrayList<>();

	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	@ManyToMany(mappedBy = "favorite", fetch = FetchType.LAZY)
	private List<ShopUser> userFavotite = new ArrayList<>();

	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	private List<Basket> baskets = new ArrayList<>();

	public Book(String name, String bookSeries, int bookInSeries, int imprintDate, int pages, BookCover bookCover,
			String language, String description, BigDecimal price, int availability, String image, Genre genre,
			boolean visible) {
		super();
		this.name = name;
		this.bookSeries = bookSeries;
		this.bookInSeries = bookInSeries;
		this.imprintDate = imprintDate;
		this.pages = pages;
		this.bookCover = bookCover;
		this.language = language;
		this.description = description;
		this.price = price;
		this.availability = availability;
		this.image = image;
		this.genre = genre;
		this.visible = visible;
	}

	public Book(String name, String bookSeries, int imprintDate, int pages, BookCover bookCover, String language,
			String description, Genre genre, BigDecimal price, int availability, List<Author> authors) {
		super();
		this.name = name;
		this.bookSeries = bookSeries;
		this.imprintDate = imprintDate;
		this.pages = pages;
		this.bookCover = bookCover;
		this.language = language;
		this.description = description;
		this.genre = genre;
		this.price = price;
		this.availability = availability;
		this.authors = authors;
	}

	public Book(String name, String bookSeries, int imprintDate, int pages, BookCover bookCover, String language,
			String description, Genre genre, BigDecimal price, int availability) {
		super();
		this.name = name;
		this.bookSeries = bookSeries;
		this.imprintDate = imprintDate;
		this.pages = pages;
		this.bookCover = bookCover;
		this.language = language;
		this.description = description;
		this.genre = genre;
		this.price = price;
		this.availability = availability;
	}

	public Book(long id, String name, List<Author> authors, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.price = price;
	}

	public Book(long id, String image, String name, List<Author> authors, BigDecimal price) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.authors = authors;
		this.price = price;
	}

	public Book(long id, String image, String name, BigDecimal price, int availability) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.price = price;
		this.availability = availability;
	}

	public Book(long id, String name, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Book() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBookSeries() {
		return bookSeries;
	}

	public void setBookSeries(String bookSeries) {
		this.bookSeries = bookSeries;
	}

	public int getBookInSeries() {
		return bookInSeries;
	}

	public void setBookInSeries(int bookInSeries) {
		this.bookInSeries = bookInSeries;
	}

	public int getImprintDate() {
		return imprintDate;
	}

	public void setImprintDate(int imprintDate) {
		this.imprintDate = imprintDate;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public BookCover getBookCover() {
		return bookCover;
	}

	public void setBookCover(BookCover bookCover) {
		this.bookCover = bookCover;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
		if (!genre.getBooks().contains(this)) {
			genre.addBook(this);
		}
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setPrice(String price) {
		this.price = new BigDecimal(price);
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<ShopUser> getUserFavotite() {
		return userFavotite;
	}

	public void setUserFavotite(List<ShopUser> userFavotite) {
		this.userFavotite = userFavotite;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getPopulary() {
		return populary;
	}

	public void setPopulary(int populary) {
		this.populary = populary;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public List<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(List<Basket> baskets) {
		this.baskets = baskets;
	}

	public void addAuthor(Author author) {
		if (!authors.contains(author)) {
			authors.add(author);
		}
		if (!author.getBooks().contains(this)) {
			author.addBook(this);
		}
	}

	public void addUserFavorite(ShopUser user) {
		if (!userFavotite.contains(user)) {
			userFavotite.add(user);
		}
		if (!user.getFavorite().contains(this)) {
			user.addInFavorite(this);
		}
	}

	public void addOrder(Order order) {
		if (!orders.contains(order)) {
			orders.add(order);
		}
		if (!order.getBooks().contains(this)) {
			order.addBook(this);
		}
	}

	public void addBasket(Basket basket) {
		if (!baskets.contains(basket)) {
			baskets.add(basket);
		}
	}

	public void incAvailability() {
		this.availability++;
	}

	public void decAvailability() {
		this.availability--;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", bookSeries=" + bookSeries + ", bookInSeries=" + bookInSeries
				+ ", imprintDate=" + imprintDate + ", pages=" + pages + ", bookCover=" + bookCover + ", language="
				+ language + ", description=" + description + ", genre=" + genre + ", price=" + price
				+ ", availability=" + availability + ", image=" + (image != null) + ", " + authors + ", populary="
				+ populary + "]";
	}
}
