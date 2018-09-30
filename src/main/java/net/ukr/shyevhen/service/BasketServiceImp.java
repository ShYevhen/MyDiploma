package net.ukr.shyevhen.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.shyevhen.model.Basket;
import net.ukr.shyevhen.model.Book;
import net.ukr.shyevhen.repository.BasketRepository;

@Service
public class BasketServiceImp implements BasketService {
	@Autowired
	private BasketRepository basketRepository;

	@Override
	@Transactional(readOnly=true)
	public List<Book> findBySession(String session) {
		return basketRepository.findBySession(session);
	}

	@Override
	@Transactional(readOnly=true)
	public int countBySession(String session) {
		return basketRepository.countBySession(session);
	}

	@Override
	@Transactional
	public void addToBasket(Basket basket) {
		basketRepository.save(basket);
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		basketRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public void deleteBySession(String session) {
		basketRepository.deleteBySession(session);
	}

	@Override
	@Transactional
	public void deleteByDate(Date day) {
		basketRepository.deleteByDate(day);
	}

}
