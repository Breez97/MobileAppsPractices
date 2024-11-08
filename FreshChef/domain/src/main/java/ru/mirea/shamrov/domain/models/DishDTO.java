package ru.mirea.shamrov.domain.models;

public class DishDTO {

	private Integer id;
	private String image;
	private String title;
	private String description;
	private Double price;
	private Integer grams;

	public DishDTO(Integer id, String image, String title, String description, Double price, Integer grams) {
		this.id = id;
		this.image = image;
		this.title = title;
		this.description = description;
		this.price = price;
		this.grams = grams;
	}

	public Integer getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getGrams() {
		return grams;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setGrams(Integer grams) {
		this.grams = grams;
	}

}
