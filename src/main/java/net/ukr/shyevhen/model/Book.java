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
	private String description;
	private String genre;
	private BigDecimal price;
	private int availability;
	private byte[] image;
	private boolean visible;
	private int populary;
	private Date addDate;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	@ManyToMany(fetch = FetchType.EAGER)//mappedBy = "books", 
	private List<Author> authors = new ArrayList<>();

	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	@ManyToMany(mappedBy = "favorite", fetch = FetchType.LAZY)
	private List<ShopUser> userFavotite = new ArrayList<>();
	
	@OneToMany(mappedBy="book", fetch = FetchType.LAZY)
	private List<Basket> baskets = new ArrayList<>();
	
	public Book(String name, String bookSeries, int imprintDate, int pages, BookCover bookCover, String language,
			String description, String genre, BigDecimal price, int availability, List<Author> authors) {
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
			String description, String genre, BigDecimal price, int availability) {
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
		this.id=id;
		this.name = name;
		this.authors = authors;
		this.price = price;
	}
	
	public Book(long id, String name, BigDecimal price) {
		super();
		this.id=id;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
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
		if(!authors.contains(author)) {
			authors.add(author);
		}
		if(!author.getBooks().contains(this)) {
			author.addBook(this);
		}
	}
	
	public void addUserFavorite(ShopUser user) {
		if(!userFavotite.contains(user)) {
			userFavotite.add(user);
		}
		if(!user.getFavorite().contains(this)) {
			user.addInFavorite(this);
		}
	}
	
	public void addOrder(Order order) {
		if(!orders.contains(order)) {
			orders.add(order);
		}
		if(!order.getBooks().contains(this)) {
			order.addBook(this);
		}
	}
	
	public void addBasket(Basket basket) {
		if(!baskets.contains(basket)) {
			baskets.add(basket);
		}
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", bookSeries=" + bookSeries +", bookInSeries=" + bookInSeries + ", imprintDate=" + imprintDate
				+ ", pages=" + pages + ", bookCover=" + bookCover + ", language=" + language + ", description="
				+ description + ", genre=" + genre + ", price=" + price + ", availability=" + availability + ", image="
				+ (image != null) + ", " + authors + ", populary="+populary+/*", addDate="+sdf.format(addDate)+*/"]";
	}
}
