package ru.mirea.shamrov.domain.repository;

import ru.mirea.shamrov.domain.models.DishRetrofitDTO;

public interface DishRetrofitRepository {

	interface Callback {
		void onSuccess(DishRetrofitDTO dishRetrofitDTO);
		void onError(Throwable throwable);
	}

	void getRandomDish(Callback callBack);

}
