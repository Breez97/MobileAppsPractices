package ru.mirea.shamrov.domain.models;

public class DishDatabaseDTO {

	private String title;
	private Double price;
	private String category;
	private String area;
	private String instructions;
	private String imageUrl;

	public DishDatabaseDTO(String title, Double price, String category, String area, String instructions, String imageUrl) {
		this.title = title;
		this.price = price;
		this.category = category;
		this.area = area;
		this.instructions = instructions;
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Title: " + title +
				"\nPrice: " + price;
	}

}
