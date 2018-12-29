package net.ukr.shyevhen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;

public interface UserRepository extends JpaRepository<ShopUser, Long> {

	@Query("SELECT u FROM ShopUser u left join fetch u.favorite WHERE u.login = :login")
	ShopUser getUserFavoriteList(@Param("login") String login);

	@Query("SELECT NEW net.ukr.shyevhen.model.ShopUser(u.id, u.login, u.name, u.surname, u.role) FROM ShopUser u ORDER BY u.login ASC")
	List<ShopUser> findAllOrderByLoginASC();

	@Query("SELECT NEW net.ukr.shyevhen.model.ShopUser(u.id, u.login, u.name, u.surname, u.role) FROM ShopUser u WHERE u.role = :role")
	List<ShopUser> findShopUserByRole(@Param("role") Role role);

	ShopUser getById(@Param("id") long id);

	ShopUser getByLogin(@Param("login") String login);

	@Query("SELECT NEW net.ukr.shyevhen.model.ShopUser(u.id, u.role) FROM ShopUser u WHERE u.login = :login")
	ShopUser getIdAndRoleByLogin(@Param("login") String login);

	boolean existsByLogin(@Param("login") String login);

}
