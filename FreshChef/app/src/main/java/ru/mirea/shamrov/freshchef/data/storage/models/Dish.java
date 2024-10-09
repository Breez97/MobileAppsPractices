package ru.mirea.shamrov.freshchef.data.storage.models;

import java.time.LocalDate;

public class Dish {

	private int id;
	private String title;
	private Double price;
	private LocalDate localDate;

	public Dish(int id, String title, Double price, LocalDate localDate) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.localDate = localDate;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Double getPrice() {
		return price;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
}
