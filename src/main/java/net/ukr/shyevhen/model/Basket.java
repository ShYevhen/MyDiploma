package net.ukr.shyevhen.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Baskets")
public class Basket {

	@Id
	@GeneratedValue
	private long id;
	
	private String session;
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	private Book book;

	public Basket(String session, Book book) {
		super();
		this.session = session;
		this.book = book;
		this.date = new Date();
	}

	public Basket(long id, String session, Book book, Date date) {
		super();
		this.id = id;
		this.session = session;
		this.book = book;
		this.date = date;
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

	public void setSessionId(String session) {
		this.session = session;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
		this.book.addBasket(this);
	}
}
