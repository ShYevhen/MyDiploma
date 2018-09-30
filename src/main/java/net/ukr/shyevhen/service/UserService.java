package net.ukr.shyevhen.service;

import java.util.List;


import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;

public interface UserService {
	
	List<ShopUser> findUsersByRole(Role role);
	List<ShopUser> findAllOrderByLoginASC();
	ShopUser getUserFavoriteList(String login);
	ShopUser getByUserLogin(String login);
	ShopUser getById(long id);
	ShopUser getIdAndRoleByLogin(String login);
	boolean existByUserLogin(String login);
	void addUser(ShopUser user);
	void changeUserData(ShopUser user);
	void deleteUser(long id);
}
