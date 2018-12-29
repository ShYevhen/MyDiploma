package net.ukr.shyevhen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.shyevhen.model.Genre;
import net.ukr.shyevhen.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired
	private GenreRepository genreRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Genre> findAllOrderByPath() {
		return genreRepository.findAllOrderByPath();
	}

	@Override
	@Transactional
	public void addGenre(Genre genre) {
		genreRepository.save(genre);
	}

	@Override
	@Transactional
	public void changeGenre(Genre genre) {
		genreRepository.save(genre);
	}

	@Override
	@Transactional(readOnly = true)
	public Genre getGenreByPath(String path) {
		return genreRepository.getOneByPath(path);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Genre> findGenreByPath(String path) {
		return genreRepository.findAllByPath(path);
	}

	@Override
	public List<Genre> findGenreByPathExept(String path) {
		return genreRepository.findAllByPathExept(path, path);
	}

	@Override
	public List<Genre> findGenreByPathExepts(String path, String exept) {
		return genreRepository.findAllByPathExepts(path, exept, path);
	}

	@Override
	@Transactional(readOnly = true)
	public String getGenreNameByPath(String path) {
		return genreRepository.getNameByPath(path);
	}

	@Override
	public long countGenres() {
		return genreRepository.count();
	}
}
