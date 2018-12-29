package net.ukr.shyevhen.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Baskets")
public class Basket {

	@Id
	@GeneratedValue
	private long id;

	private String session;
	private Date date;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private List<Book> books = new ArrayList<>();

	public Basket(long id, String session, Date date, List<Book> books) {
		super();
		this.id = id;
		this.session = session;
		this.date = date;
		this.books = books;
	}

	public Basket(long id, String session, List<Book> books) {
		super();
		this.id = id;
		this.session = session;
		this.books = books;
	}

	public Basket(long id, List<Book> books) {
		super();
		this.id = id;
		this.books = books;
	}

	public Basket(List<Book> books) {
		super();
		this.books = books;
	}

	public Basket(String session) {
		super();
		this.session = session;
		this.date = new Date();
	}

	public Basket() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	@Override
	public String toString() {
		return "Basket [id=" + id + ", session=" + session + ", date=" + date + ", books=" + books + "]";
	}
	
	
}
