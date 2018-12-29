package net.ukr.shyevhen.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String surname;
	private String phone;
	private String address;
	private Date createDate;
	private Date deliveryDate;
	private boolean status;
	private BigDecimal deliveryPrice;
	private BigDecimal totalPrice;
	private int booksCount;

	@ManyToOne(fetch = FetchType.EAGER)
	private ShopUser user;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private List<Book> books = new ArrayList<>();

	public Order(String name, String surname, String phone, String address, BigDecimal deliveryPrice,
			BigDecimal totalPrice, List<Book> books) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.deliveryPrice = deliveryPrice;
		this.totalPrice = totalPrice;
		this.books = books;
		this.createDate = new Date();
		this.booksCount = books.size();
	}

	public Order(String name, String surname, String phone, String address, BigDecimal deliveryPrice,
			BigDecimal totalPrice, ShopUser user, List<Book> books) {
		super();
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.deliveryPrice = deliveryPrice;
		this.totalPrice = totalPrice;
		this.user = user;
		this.books = books;
		this.createDate = new Date();
		this.booksCount = books.size();
	}

	public Order(long id, Date createDate, int booksCount, BigDecimal totalPrice, boolean status) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.booksCount = booksCount;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public Order(long id, boolean status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Order() {
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public BigDecimal getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(BigDecimal deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ShopUser getUser() {
		return user;
	}

	public void setUser(ShopUser user) {
		this.user = user;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
		this.booksCount = books.size();
	}

	public int getBooksCount() {
		return booksCount;
	}

	public void setBooksCount(int booksCount) {
		this.booksCount = booksCount;
	}

	public void addBook(Book book) {
		if (!books.contains(book)) {
			books.add(book);
		}
		if (!book.getOrders().contains(this)) {
			book.addOrder(this);
		}
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", surname=" + surname + ", phone=" + phone + ", address="
				+ address + ", createDate=" + createDate + ", deliveryDate=" + deliveryDate + ", status=" + status
				+ ", deliveryPrice=" + deliveryPrice + ", totalPrice=" + totalPrice + ", booksCount=" + booksCount
				+ ", " + user + ", " + books + "]";
	}
}
