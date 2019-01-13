package net.ukr.shyevhen.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.shyevhen.model.Order;
import net.ukr.shyevhen.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	@Transactional(readOnly = true)
	public boolean existOrder(String name, String surname, BigDecimal deliveryPrice, BigDecimal totalPrice) {
		return orderRepository.existOrder(name, surname, deliveryPrice, totalPrice);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existOrder(long id) {
		return orderRepository.existOrder(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findByUserId(long id, Pageable pageable) {
		return orderRepository.findByUserId(id, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findByUserLogin(String login, Pageable pageable) {
		return orderRepository.findByUserLogin(login, pageable);
	}

	@Override
	@Transactional
	public List<Order> findByUserLogin(String login) {
		return orderRepository.findByUserLogin(login);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findByStatusOrderByCreateDate(Pageable pageable) {
		List<Order> orders = orderRepository.findAllOrderByCreateDateAndStatus(pageable);
		return orders;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findByOrderId(String login, long id, long exept) {
		return orderRepository.findByOrderId(login, id, exept);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findByOrderId(long id, long exept) {
		return orderRepository.findByOrderId(id, exept);
	}

	@Override
	@Transactional(readOnly = true)
	public Order getOrder(String login, long id) {
		return orderRepository.getById(login, id);
	}

	@Override
	@Transactional
	public Order getOrder(long id) {
		return orderRepository.getById(id);
	}

	@Override
	@Transactional
	public void addOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	@Transactional
	public void changeOrderData(Order order) {
		orderRepository.save(order);
	}

	@Override
	@Transactional
	public void deleteOrder(long id) {
		orderRepository.deleteById(id);
	}

	@Override
	@Transactional
	public int countOrders() {
		return orderRepository.countOrders();
	}

	@Override
	@Transactional
	public int countOrders(String login) {
		return orderRepository.countOrdersByUser(login);
	}

}
