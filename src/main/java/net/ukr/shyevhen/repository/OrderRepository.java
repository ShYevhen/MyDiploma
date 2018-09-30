package net.ukr.shyevhen.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE o.user.login = :login ORDER BY o.createDate ASC")
	List<Order> findByUserLogin(@Param("login") String login);
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE o.user.id = :id ORDER BY o.createDate ASC")
	List<Order> findByUserId(@Param("id") long id);
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE o.status = false ORDER BY o.createDate ASC")
	List<Order> findByStatusOrderByCreateDate();
	
	Order getById(@Param("id") long id);
	
	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Order o WHERE o.name = :name AND o.surname = :surname AND"
			+ " o.createDate = :createDate AND o.booksCount = :booksCount AND o.totalPrice = :totalPrice")
	boolean existOrder(@Param("name") String name,@Param("surname") String surname,@Param("createDate") Date createDate, @Param("booksCount") int booksCount, @Param("totalPrice") BigDecimal totalPrice);
}
