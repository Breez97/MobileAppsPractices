package ru.mirea.shamrov.domain.models;

public class DishDTO {

	private Integer id;
	private String title;
	private Double price;

	public DishDTO(Integer id, String title, Double price) {
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

	public Double getPrice() {
		return price;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "id: " + id +
				"\nTitle: " + title +
				"\nPrice: " + price;
	}

}
