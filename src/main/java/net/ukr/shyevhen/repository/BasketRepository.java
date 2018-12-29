package net.ukr.shyevhen.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.ukr.shyevhen.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

	@Query("SELECT b.id FROM Basket b WHERE b.session = :session")
	List<Long> getIdBySession(@Param("session") String session);

	@Query("SELECT COUNT(bk.id) FROM Basket b JOIN b.books bk WHERE b.session = :session")
	int countByBookId(@Param("session") String session);

	@Modifying
	@Query("DELETE FROM Basket b WHERE b.date < :day")
	void deleteByDate(@Param("day") Date day);
}
