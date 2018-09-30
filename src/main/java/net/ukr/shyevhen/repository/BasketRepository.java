package net.ukr.shyevhen.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.ukr.shyevhen.model.Basket;
import net.ukr.shyevhen.model.Book;

@Repository
public interface BasketRepository extends JpaRepository<Basket, String> {
	
	@Query("SELECT b.book FROM Basket b WHERE b.session = :session")
	List<Book> findBySession(@Param("session") String session);
	
	@Query("SELECT COUNT(b) FROM Basket b WHERE b.session = :session")
	int countBySession(@Param("session") String session);
	
	@Modifying
	@Query("DELETE FROM Basket b WHERE b.id = :id")
	void deleteById(@Param("id") long id);
	
	@Modifying
	@Query("DELETE FROM Basket b WHERE b.session = :session")
	void deleteBySession(@Param("session") String session);
	
	@Modifying
	@Query("DELETE FROM Basket b WHERE b.date < :day")//DAY
	void deleteByDate(@Param("day") Date day);
}
