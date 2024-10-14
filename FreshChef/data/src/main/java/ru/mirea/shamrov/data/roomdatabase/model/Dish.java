package ru.mirea.shamrov.data.roomdatabase.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dishes")
public class Dish {

	@ColumnInfo(name = "id")
	@PrimaryKey(autoGenerate = true)
	private Integer id;

	@ColumnInfo(name = "title")
	private String title;

	@ColumnInfo(name = "price")
	private Double price;

	public Dish(String title, Double price) {
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

}
