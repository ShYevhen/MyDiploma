package net.ukr.shyevhen.service;

import java.util.Date;
import java.util.List;

import net.ukr.shyevhen.model.Basket;
import net.ukr.shyevhen.model.Book;

public interface BasketService {
	List<Book> findBySession(String session);
	int countBySession(String session);
	void addToBasket(Basket basket);
	void deleteById(long id);
	void deleteBySession(String session);
	void deleteByDate(Date day);
}
