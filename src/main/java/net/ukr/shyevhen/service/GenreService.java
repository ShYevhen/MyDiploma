package net.ukr.shyevhen.service;

import java.util.List;

import net.ukr.shyevhen.model.Genre;

public interface GenreService {
	List<Genre> findAllOrderByPath();

	void addGenre(Genre genre);

	void changeGenre(Genre genre);

	Genre getGenreByPath(String path);

	List<Genre> findGenreByPath(String path);

	List<Genre> findGenreByPathExept(String path);

	List<Genre> findGenreByPathExepts(String path, String exept);

	String getGenreNameByPath(String path);
	
	long countGenres();
}
