package ru.mirea.shamrov.data.retrofit.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.mirea.shamrov.data.retrofit.datasource.ApiResponse;

public interface ApiService {

	@GET("api/json/v1/1/random.php")
	Call<ApiResponse> getRandomDish();

	@GET("api/json/v1/1/lookup.php?i={id}")
	Call<ApiResponse> getDishById(@Path("id") Integer id);

}
