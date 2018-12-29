package net.ukr.shyevhen.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.shyevhen.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE o.user.login = :login")
	List<Order> findByUserLogin(@Param("login") String login, Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE o.user.login = :login"
			+ " ORDER BY o.createDate DESC")
	List<Order> findByUserLogin(@Param("login") String login);

	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE o.user.id = :id")
	List<Order> findByUserId(@Param("id") long id, Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE o.status = true")
	List<Order> findByStatusTOrderByCreateDate(Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE o.status = false")
	List<Order> findByStatusFOrderByCreateDate(Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o")
	List<Order> findAllOrderByCreateDateAndStatus(Pageable pageable);

	@Query("SELECT COUNT(o.id) FROM Order o")
	int countOrders();

	@Query("SELECT COUNT(o.id) FROM Order o WHERE o.user.login = :login")
	int countOrdersByUser(@Param("login") String login);

	@Query(value = "SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE"
			+ " o.id LIKE CONCAT(:id,'%') AND o.id NOT LIKE :exept ORDER BY o.createDate DESC")
	List<Order> findByOrderId(@Param("id") long id, @Param("exept") long exept);

	@Query(value = "SELECT NEW net.ukr.shyevhen.model.Order(o.id, o.createDate, o.booksCount, o.totalPrice, o.status) FROM Order o WHERE"
			+ " o.user.login = :login AND o.id LIKE CONCAT(:id,'%') AND o.id NOT LIKE :exept ORDER BY o.createDate DESC")
	List<Order> findByOrderId(@Param("login") String login, @Param("id") long id, @Param("exept") long exept);

	Order getById(@Param("id") long id);

	@Query("SELECT o FROM Order o left join fetch o.books WHERE o.user.login = :login AND o.id = :id ")
	Order getById(@Param("login") String login, @Param("id") long id);

	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Order o WHERE o.name = :name AND o.surname = :surname AND"
			+ " o.deliveryPrice = :deliveryPrice AND o.totalPrice = :totalPrice")
	boolean existOrder(@Param("name") String name, @Param("surname") String surname,
			@Param("deliveryPrice") BigDecimal deliveryPrice, @Param("totalPrice") BigDecimal totalPrice);

	@Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Order o WHERE o.id = :id")
	boolean existOrder(@Param("id") long id);

}
