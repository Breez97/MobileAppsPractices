package ru.mirea.shamrov.data.api.model;

public class DishApi {

	private String title;
	private Double price;

	public DishApi(String title, Double price) {
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public Double getPrice() {
		return price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}