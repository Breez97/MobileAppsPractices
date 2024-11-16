package ru.mirea.shamrov.domain.models;

public class DishRetrofitDTO {

	private Integer id;
	private String title;
	private String category;
	private String imageUrl;
	private Integer amount;
	private Double price;

	public DishRetrofitDTO(Integer id, String title, String category, String imageUrl, Integer amount, Double price) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.imageUrl = imageUrl;
		this.amount = amount;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
