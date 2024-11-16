package ru.mirea.shamrov.data.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiDishRetrofit {
	@SerializedName("idMeal")
	@Expose
	private Integer idMeal;

	@SerializedName("strMeal")
	@Expose
	private String title;

	@SerializedName("strCategory")
	@Expose
	private String category;

	@SerializedName("strMealThumb")
	@Expose
	private String imageUrl;

	public Integer getIdMeal() {
		return idMeal;
	}

	public void setIdMeal(Integer idMeal) {
		this.idMeal = idMeal;
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

}