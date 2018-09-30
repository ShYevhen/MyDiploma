package net.ukr.shyevhen.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Authors")
public class Author {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String surname;
	private Date birthday;
	private String biography;
	private byte[] image;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	@ManyToMany(mappedBy= "authors", cascade= {CascadeType.MERGE,CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@OrderBy("bookSeries ASC, bookInSeries ASC")
	private List<Book> books = new ArrayList<>();

	

	public Author(String name, String surname, Date birthday, String biography) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.biography = biography;
	}
	
	public Author(long id, String name, String surname) {
		super();
		this.id=id;
		this.name = name;
		this.surname = surname;
	}

	public Author() {
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
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		if(!books.contains(book)) {
			books.add(book);
		}
		if(!book.getAuthors().contains(this)) {
			book.addAuthor(this);
		}
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", surname=" + surname + ", birthday=" + sdf.format(birthday) + ", biography=" 
	+ biography + ", image=" + (image!=null) +/* ", books=" + books +*/ "]";
	}
	
	
}
