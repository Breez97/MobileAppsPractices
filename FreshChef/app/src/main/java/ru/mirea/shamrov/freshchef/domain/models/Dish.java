package ru.mirea.shamrov.freshchef.domain.models;

public class Dish {

	private Integer id;
	private String title;
	private Double price;

	public Dish(Integer id, String title, Double price) {
		this.id = id;
		this.title = title;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "id: " + id +
				"\nTitle: " + title +
				"\nPrice: " + price;
	}

}
