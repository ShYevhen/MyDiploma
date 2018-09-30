package net.ukr.shyevhen.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.shyevhen.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author getAuthorByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
	
	Author getAuthorById(@Param("id") long id);
	
	@Query("SELECT NEW net.ukr.shyevhen.model.Author(a.id, a.name, a.surname) FROM Author a ORDER BY a.surname ASC, a.name ASC")
	List<Author> findAllByOrderBySurnameAscNameAsc();
	
	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Author a WHERE a.name = :name AND a.surname = :surname AND"
			+ " a.birthday = :birthday")
	boolean existAuthor(@Param("name") String name, @Param("surname") String surname,
			@Param("birthday") Date birthday);
}
