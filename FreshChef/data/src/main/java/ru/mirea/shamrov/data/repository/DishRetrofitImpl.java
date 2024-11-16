package ru.mirea.shamrov.data.repository;

import java.util.Random;

import ru.mirea.shamrov.data.retrofit.ApiDataSource;
import ru.mirea.shamrov.data.retrofit.model.ApiDishRetrofit;
import ru.mirea.shamrov.domain.models.DishRetrofitDTO;
import ru.mirea.shamrov.domain.repository.DishRetrofitRepository;

public class DishRetrofitImpl implements DishRetrofitRepository {
	private final ApiDataSource apiDataSource;

	public DishRetrofitImpl(ApiDataSource apiDataSource) {
		this.apiDataSource = apiDataSource;
	}

	@Override
	public void getRandomDish(Callback callback) {
		apiDataSource.getRandomDish(new ApiDataSource.Callback() {
			@Override
			public void onSuccess(ApiDishRetrofit apiDish) {
				callback.onSuccess(mapRetrofitToDomain(apiDish));
			}

			@Override
			public void onError(Throwable throwable) {
				callback.onError(throwable);
			}
		});
	}

	private DishRetrofitDTO mapRetrofitToDomain(ApiDishRetrofit apiDishRetrofit) {
		Random random = new Random();
		return new DishRetrofitDTO(apiDishRetrofit.getIdMeal(), apiDishRetrofit.getTitle(),
				apiDishRetrofit.getCategory(), apiDishRetrofit.getImageUrl(),
				100 + random.nextInt(401), 100 + (1000 - 100) * random.nextDouble());
	}
}