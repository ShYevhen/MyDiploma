package net.ukr.shyevhen.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Genres")
public class Genre {
	@Id
	private String path;
	private String name;

	@OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
	private List<Book> books = new ArrayList<>();

	public Genre(String path, String name) {
		super();
		this.path = path;
		this.name = name;
	}

	public Genre(String path, String name, List<Book> books) {
		super();
		this.path = path;
		this.name = name;
		this.books = books;
	}

	public Genre(String path) {
		super();
		this.path = path;
	}

	public Genre() {
		super();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		if (!book.getGenre().equals(this)) {
			book.setGenre(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		Genre other = (Genre) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return path;
	}

}
