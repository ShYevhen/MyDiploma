package net.ukr.shyevhen.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.Order;
import net.ukr.shyevhen.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	@Transactional(readOnly=true)
	public boolean existOrder(String name, String surname, Date createDate, int booksCount, BigDecimal totalPrice) {
		return orderRepository.existOrder(name, surname, createDate, booksCount, totalPrice);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Order> findByUserId(long id) {
		return orderRepository.findByUserId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Order> findByUserLogin(String login) {
		return orderRepository.findByUserLogin(login);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Order> findByStatusOrderByCreateDate(){
		return orderRepository.findByStatusOrderByCreateDate();
	}
	
	@Override
	@Transactional(readOnly=true)
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
//		orderRepository.deleteById(id);
	}

	
	
	
}
