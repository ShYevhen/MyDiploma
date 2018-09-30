package net.ukr.shyevhen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<ShopUser> findUsersByRole(Role role) {
		
		return userRepository.findShopUserByRole(role);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ShopUser> findAllOrderByLoginASC() {
		return userRepository.findAllOrderByLoginASC();
	}
	@Override
	@Transactional(readOnly=true)
	public ShopUser getUserFavoriteList(String login) {
		return userRepository.getUserFavoriteList(login);
	}

	@Override
	@Transactional(readOnly=true)
	public ShopUser getByUserLogin(String login) {
		return userRepository.getByLogin(login);
	}
	
	@Override
	@Transactional(readOnly=true)
	public ShopUser getById(long id) {
		return userRepository.getById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public ShopUser getIdAndRoleByLogin(String login) {
		return userRepository.getIdAndRoleByLogin(login);
	}
	
	@Override
	@Transactional(readOnly=true)
	public boolean existByUserLogin(String login) {
		return userRepository.existsByLogin(login);
	}

	@Override
	@Transactional
	public void addUser(ShopUser user) {
		userRepository.save(user);		
	}

	@Override
	@Transactional
	public void changeUserData(ShopUser user) {
		userRepository.save(user);	
	}

	@Override
	@Transactional
	public void deleteUser(long id) {
//		userRepository.deleteById(id);
	}
	
}
