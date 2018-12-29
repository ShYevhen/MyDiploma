package net.ukr.shyevhen.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;

import net.ukr.shyevhen.model.Order;

public interface OrderService {
	List<Order> findByUserId(long id, Pageable pageable);

	List<Order> findByUserLogin(String login, Pageable pageable);

	List<Order> findByUserLogin(String login);

	List<Order> findByStatusOrderByCreateDate(Pageable pageable);

	List<Order> findByOrderId(long id, long exept);

	List<Order> findByOrderId(String login, long id, long exept);

	Order getOrder(String login, long id);

	Order getOrder(long id);

	void addOrder(Order order);

	void changeOrderData(Order order);

	void deleteOrder(long id);

	boolean existOrder(String name, String surname, BigDecimal deliveryPrice, BigDecimal totalPrice);

	boolean existOrder(long id);

	int countOrders();

	int countOrders(String login);
}
