package ru.mirea.shamrov.data.retrofit;

import ru.mirea.shamrov.data.retrofit.model.ApiDishRetrofit;

public interface ApiDataSource {

	interface Callback {
		void onSuccess(ApiDishRetrofit apiDishRetrofit);
		void onError(Throwable throwable);
	}

	void getRandomDish(Callback callback);

	void getDishById(Integer id, Callback callback);

}
