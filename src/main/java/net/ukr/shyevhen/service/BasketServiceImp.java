package net.ukr.shyevhen.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ukr.shyevhen.model.Basket;
import net.ukr.shyevhen.repository.BasketRepository;

@Service
public class BasketServiceImp implements BasketService {
	@Autowired
	private BasketRepository basketRepository;

	@Override
	@Transactional
	public Basket getBySession(String session) {
		List<Long> ids = basketRepository.getIdBySession(session);
		if (ids.size() == 1) {
			return basketRepository.getOne(ids.get(0));
		} else if (ids.size() > 1) {
			for (Long id : ids) {
				basketRepository.deleteById(id);
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public int countByBookId(String session) {
		return basketRepository.countByBookId(session);
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
	public void deleteByDate(Date day) {
		basketRepository.deleteByDate(day);
	}

	@Override
	@Transactional
	public void deleteBySession(String session) {
		List<Long> ids = basketRepository.getIdBySession(session);
		for (Long id : ids) {
			basketRepository.deleteById(id);
		}
	}

}
