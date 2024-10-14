package ru.mirea.shamrov.data.storage.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "dishes")
public class Dish {

	@ColumnInfo(name = "id")
	@PrimaryKey(autoGenerate = true)
	private Integer id;

	@ColumnInfo(name = "title")
	private String title;

	@ColumnInfo(name = "price")
	private Double price;

	@ColumnInfo(name = "localDate")
	private LocalDate localDate;

	public Dish(Integer id, String title, Double price, LocalDate localDate) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.localDate = localDate;
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

	public LocalDate getLocalDate() {
		return localDate;
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

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

}
