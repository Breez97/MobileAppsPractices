package ru.mirea.shamrov.data.retrofit.datasource;

import com.google.gson.annotations.SerializedName;
import java.util.List;

import ru.mirea.shamrov.data.retrofit.model.ApiDishRetrofit;

public class ApiResponse {

	@SerializedName("meals")
	private List<ApiDishRetrofit> meals;

	public List<ApiDishRetrofit> getMeals() {
		return meals;
	}

	public void setMeals(List<ApiDishRetrofit> meals) {
		this.meals = meals;
	}

}