package net.ukr.shyevhen.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.shyevhen.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author getAuthorByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

	Author getAuthorById(@Param("id") long id);

	@Query("SELECT NEW net.ukr.shyevhen.model.Author(a.id, a.name, a.surname) FROM Author a")
	List<Author> findAllAuthors(Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Author(a.id, a.name, a.surname) FROM Author a WHERE a.surname LIKE :letter%")
	List<Author> findAllBySurnameLetter(@Param("letter") String letter, Pageable pageable);

	@Query("SELECT NEW net.ukr.shyevhen.model.Author(a.id, a.name, a.surname) FROM Author a WHERE a.name LIKE :name AND a.surname LIKE :surname ORDER BY a.surname ASC, a.name ASC")
	List<Author> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

	@Query("SELECT NEW net.ukr.shyevhen.model.Author(a.id, a.name, a.surname) FROM Author a WHERE a.surname LIKE :surname ORDER BY a.name ASC")
	List<Author> findBySurname(@Param("surname") String surname);

	@Query("SELECT COUNT(a) FROM Author a")
	int countAuthors();

	@Query("SELECT COUNT(a) FROM Author a WHERE a.surname LIKE :letter%")
	int countAuthorsByLetter(@Param("letter") String letter);

	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Author a WHERE a.name = :name AND a.surname = :surname AND"
			+ " a.birthday = :birthday")
	boolean existAuthor(@Param("name") String name, @Param("surname") String surname, @Param("birthday") Date birthday);
}
