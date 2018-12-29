package net.ukr.shyevhen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.ukr.shyevhen.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, String> {
	@Query("SELECT g FROM Genre g ORDER BY g.path ASC")
	List<Genre> findAllOrderByPath();

	@Query("SELECT g FROM Genre g WHERE g.path LIKE :path%")
	List<Genre> findAllByPath(@Param("path") String path);

	@Query("SELECT g FROM Genre g WHERE g.path LIKE :path% AND g.path NOT LIKE :noPath")
	List<Genre> findAllByPathExept(@Param("path") String path, @Param("noPath") String noPath);

	@Query("SELECT g FROM Genre g WHERE g.path LIKE :path% AND g.path NOT LIKE :exept% AND g.path NOT LIKE :noPath")
	List<Genre> findAllByPathExepts(@Param("path") String path, @Param("exept") String exept,
			@Param("noPath") String noPath);

	@Query("SELECT NEW net.ukr.shyevhen.model.Genre(g.path, g.name) FROM Genre g WHERE g.path = :path")
	Genre getOneByPath(@Param("path") String path);

	String getNameByPath(@Param("path") String path);
}
