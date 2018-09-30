package net.ukr.shyevhen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.ukr.shyevhen.model.Order;
import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.OrderService;
import net.ukr.shyevhen.service.UserService;

@RestController
@RequestMapping("/profile/orders")
public class OrderConrtoller {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	
	// Получение списка заказов
	@GetMapping(value = "/")
	public List<Order> userOrders() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		ShopUser sUser = userService.getIdAndRoleByLogin(login);
		if(sUser.getRole().equals(Role.USER)) {
			return orderService.findByUserId(sUser.getId());
		}else {
			return orderService.findByStatusOrderByCreateDate();
		}
	}

	// Получение подробной информации о заказе
	@GetMapping(value = "/{id}")
	public Order userOrder(@PathVariable Long id) {
		Order order = orderService.getOrder(id);
		return order;
	}

	// -------------------------Нет в таблице---------------------------
	
	
	// Удаление заказа
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void userOrderDelete(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}

	// Поиск заказа по id
	@GetMapping(value = "/search/{id}")
	public Order userOrderSearch(@PathVariable Long id) {
		Order order = orderService.getOrder(id);
		return order;
	}
	
	// Поиск заказа по login
	@GetMapping(value = "/search/{login}")
	public List<Order> userOrdersSearch(@PathVariable String login) {
		return orderService.findByUserLogin(login);
	}
	
	
	@PostMapping(value = "/add")
	public ModelAndView userOrderAdd(@RequestBody Order order) {
		if(!orderService.existOrder(order.getName(), order.getSurname(), order.getCreateDate(), order.getBooksCount(),order.getTotalPrice())) {
			orderService.addOrder(order);
		}
		return new ModelAndView("redirect:/profile/orders/"+order.getId());
	}

	// --------------------------Only for admin and operators-------------
	// Изменение заказа
	@PostMapping(value = "/update")
	public ModelAndView userOrderChange(@RequestBody Order order) {
		if(orderService.existOrder(order.getName(), order.getSurname(), order.getCreateDate(), order.getBooksCount(),order.getTotalPrice())) {
			orderService.changeOrderData(order);
		}
		return new ModelAndView("redirect:/profile/orders/"+order.getId());
	}
}
