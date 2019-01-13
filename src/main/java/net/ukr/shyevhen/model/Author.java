package net.ukr.shyevhen.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Authors")
public class Author {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String surname;
	private Date birthday;
	@Lob
	@Type(type = "text")
	private String biography;
	private String image;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

	@ManyToMany(mappedBy = "authors", cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
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
		this.id = id;
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

	public String getBirthday() {
		if (birthday == null)
			return null;
		return sdf.format(birthday);
	}

	public Date getBirthdayDate() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		try {
			this.birthday = sdf.parse(birthday);
		} catch (ParseException e) {
			try {
				this.birthday = sdf2.parse(birthday);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		if (!books.contains(book)) {
			books.add(book);
		}
		if (!book.getAuthors().contains(this)) {
			book.addAuthor(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", surname=" + surname + ", birthday=" + sdf.format(birthday)
				+ ", biography=" + biography + ", image=" + (image != null) + /* ", books=" + books + */ "]";
	}

}
