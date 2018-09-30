package net.ukr.shyevhen.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name="Users")
public class ShopUser {


	@Id
	@GeneratedValue
	private long id;
//	@UniqueElements
	private String login;
	private String password;
	private String name;
	private String surname;
	private String eMail;
	private String phone;
	private String address;
	private byte[] image;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "users_books", joinColumns = {@JoinColumn(name="user_id")}, inverseJoinColumns= {@JoinColumn(name="book_id")})
	private List<Book> favorite = new ArrayList<>();
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	public ShopUser(String login, String password, String name, String surname, String eMail, String phone, Role role) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.eMail = eMail;
		this.phone = phone;
		this.role = role;
	}
	
	public ShopUser(long id, String login, String name, String surname, Role role) {
		super();
		this.id = id;
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.role = role;
	}
	
	public ShopUser(long id, Collection<Book> favorite) {
		super();
		this.id = id;
		this.favorite = (List<Book>)favorite;
	}
	
	public ShopUser(long id, Role role) {
		super();
		this.id = id;
		this.role = role;
	}
	
	public ShopUser(long id) {
		super();
		this.id = id;
	}

	public ShopUser() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Book> getFavorite() {
		return favorite;
	}

	public void setFavorite(List<Book> favorite) {
		this.favorite = favorite;
	}

	public List<Order> getOrdes() {
		return orders;
	}

	public void setOrdes(List<Order> ordes) {
		this.orders = ordes;
	}
	
	public void addInFavorite(Book book) {
		if(!favorite.contains(book)) {
			favorite.add(book);
		}
		if(!book.getUserFavotite().contains(this)) {
			book.addUserFavorite(this);
		}
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + ", surname="
				+ surname + ", eMail=" + eMail + ", phone=" + phone + ", address=" + address + ", image="
				+ (image!=null) + ", role=" + role + "]";
	}
	
	
}
