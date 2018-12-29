package net.ukr.shyevhen.service;

import java.util.Date;

import net.ukr.shyevhen.model.Basket;

public interface BasketService {
	Basket getBySession(String session);

	int countByBookId(String session);

	void addToBasket(Basket basket);

	void deleteById(long id);

	void deleteByDate(Date day);

	void deleteBySession(String session);
}
