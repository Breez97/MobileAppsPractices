package ru.mirea.shamrov.domain.models;

public class DishDatabaseDTO {

	private String title;
	private Double price;

	public DishDatabaseDTO(String title, Double price) {
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

	@Override
	public String toString() {
		return "Title: " + title +
				"\nPrice: " + price;
	}

}
