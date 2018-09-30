package net.ukr.shyevhen.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.Order;

public interface OrderService {
	List<Order> findByUserId(long id);
	List<Order> findByUserLogin(String login);
	List<Order> findByStatusOrderByCreateDate();
	Order getOrder(long id);
	void addOrder(Order order);
	void changeOrderData(Order order);
	void deleteOrder(long id);
	boolean existOrder(String name, String surname, Date createDate, int booksCount, BigDecimal totalPrice);
}
