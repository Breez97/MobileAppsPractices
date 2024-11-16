package ru.mirea.shamrov.data.retrofit.service;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.mirea.shamrov.data.retrofit.datasource.ApiResponse;

public interface ApiService {

	@GET("api/json/v1/1/random.php")
	Call<ApiResponse> getRandomDish();

}
