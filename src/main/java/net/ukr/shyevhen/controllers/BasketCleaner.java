package net.ukr.shyevhen.controllers;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import net.ukr.shyevhen.service.BasketService;

public class BasketCleaner implements Runnable {

	private BasketService basketService;

	public BasketCleaner(BasketService basketService) {
		super();
		this.basketService = basketService;
		Thread th = new Thread(this);
		th.start();
	}

	public BasketCleaner() {
		super();
		Thread th = new Thread(this);
		th.start();
	}

	public BasketService getBasketService() {
		return basketService;
	}

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	@Override
	public void run() {
		for (;;) {
			try {
				TimeUnit.HOURS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Date day = new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000));
			basketService.deleteByDate(day);
		}
	}

}
