package ru.mirea.shamrov.data.retrofit.datasource;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirea.shamrov.data.retrofit.ApiDataSource;
import ru.mirea.shamrov.data.retrofit.model.ApiDishRetrofit;
import ru.mirea.shamrov.data.retrofit.service.ApiService;

public class ApiDataSourceRetrofit implements ApiDataSource {

	public static final String BASE_URL = "https://www.themealdb.com/";
	private final ApiService apiService;

	public ApiDataSourceRetrofit() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		apiService = retrofit.create(ApiService.class);
	}

	@Override
	public void getRandomDish(ApiDataSource.Callback callback) {
		Call<ApiResponse> call = apiService.getRandomDish();
		call.enqueue(new retrofit2.Callback<ApiResponse>() {
			@Override
			public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
				if (response.isSuccessful() && response.body() != null && response.body().getMeals() != null && !response.body().getMeals().isEmpty()) {
					ApiDishRetrofit dish = response.body().getMeals().get(0);
					if (dish.getTitle() != null && dish.getCategory() != null) {
						callback.onSuccess(dish);
					} else {
						callback.onError(new Exception("Неполная информация о блюде"));
					}
				} else {
					callback.onError(new Exception("Ошибка: " + response.code()));
				}
			}

			@Override
			public void onFailure(Call<ApiResponse> call, Throwable throwable) {
				callback.onError(throwable);
			}
		});
	}

}