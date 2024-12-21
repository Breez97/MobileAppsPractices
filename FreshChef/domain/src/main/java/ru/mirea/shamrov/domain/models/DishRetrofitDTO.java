package ru.mirea.shamrov.domain.models;

public class DishRetrofitDTO {

	private Integer id;
	private String title;
	private String category;
	private String imageUrl;
	private Integer amount;
	private Double price;
	private String area;
	private String instructions;

	public DishRetrofitDTO(Integer id, String title, String category, String imageUrl, Integer amount, Double price, String area, String instructions) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.imageUrl = imageUrl;
		this.amount = amount;
		this.price = price;
		this.area = area;
		this.instructions = instructions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

}
