package net.ukr.shyevhen.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.UserService;

@RestController
@RequestMapping("/profile")
public class UserController {
	@Autowired
	private UserService userService;
	//Главная страница профиля пользователя со всей контактной информацией
	@GetMapping(value = "/")
	public ShopUser userPage(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		ShopUser shopUser = userService.getByUserLogin(login);
		return shopUser;
	}
	//Изменение информации о пользователе, возможность менять роли только для админа
	@PostMapping(value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public void userUpdate(@RequestBody ShopUser userSh) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		if(!userSh.getLogin().equalsIgnoreCase(login)) {
			return;
		}
		userService.changeUserData(userSh);
	}
	//Получение списка книг добавленных в избранное
	@GetMapping(value = "/favorite")
	public List<Book> userFavorite() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		return userService.getUserFavoriteList(login).getFavorite();
	}
	//Удаление книги из избранного
	@DeleteMapping(value = "/favorite/{id}")
	public ModelAndView userFavoriteDelete(@RequestParam("id") int bookId) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String login = user.getUsername();
		ShopUser sUser = userService.getUserFavoriteList(login);
		List<Book> books = sUser.getFavorite();
		for (Book book : books) {
			if(book.getId()==bookId) {
				books.remove(book);
				break;
			}
		}
		userService.changeUserData(sUser);
		return new ModelAndView("redirect:/profile/favorite");
	}
	
//-------------------------Only for admin---------------------------		
	//Получение списка администраторов и операторов
	@GetMapping(value = "/operators")
	public List<ShopUser> operatorsList() {
		List<ShopUser> userList = userService.findUsersByRole(Role.ADMIN);
		userList.addAll(userService.findUsersByRole(Role.OPERATOR));
		return userList;
	}
	
	//Добавнение нового оператора или администратора
	@PostMapping(value = "/operators")
	public ModelAndView addOperator(@RequestBody ShopUser sUser) {
		if(!userService.existByUserLogin(sUser.getLogin())) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			sUser.setPassword(encoder.encode(sUser.getPassword()));
			userService.addUser(sUser);
		}
		return new ModelAndView("redirect:/profile/operators");
	}
	//Удаление администратора или пользователя
	@DeleteMapping(value = "/operators/{id}")
	public ModelAndView deleteOperator(@RequestParam("id") long id) {
		userService.deleteUser(id);
		return new ModelAndView("redirect:/profile/operators");
	}
	//Изменение пав доступа оператора или администратора
	@PostMapping(value = "/operators/update")
	public ModelAndView changeOperator(@RequestBody ShopUser sUser) {
//		ShopUser dbUser = userService.getByUserLogin(sUser.getLogin());
		userService.changeUserData(sUser);
		return new ModelAndView("redirect:/profile/operators");
	}
}
