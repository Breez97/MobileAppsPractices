package ru.mirea.shamrov.data.roomdatabase.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dishes")
public class DishDatabase {

	@ColumnInfo(name = "id")
	@PrimaryKey(autoGenerate = true)
	private Integer id;

	@ColumnInfo(name = "title")
	private String title;

	@ColumnInfo(name = "price")
	private Double price;

	@ColumnInfo(name = "image")
	private String imageUrl;

	@ColumnInfo(name = "instructions")
	private String instructions;

	@ColumnInfo(name = "category")
	private String category;

	@ColumnInfo(name = "area")
	private String area;

	public DishDatabase(String title, Double price, String imageUrl, String instructions, String category, String area) {
		this.title = title;
		this.price = price;
		this.imageUrl = imageUrl;
		this.instructions = instructions;
		this.category = category;
		this.area = area;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
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

}
