package ru.mirea.shamrov.domain.usecases.retrofit;

import ru.mirea.shamrov.domain.models.DishRetrofitDTO;
import ru.mirea.shamrov.domain.repository.DishRetrofitRepository;

public class GetRandomDishRetrofitUseCase {

	private final DishRetrofitRepository dishRetrofitRepository;

	public GetRandomDishRetrofitUseCase(DishRetrofitRepository dishRetrofitRepository) {
		this.dishRetrofitRepository = dishRetrofitRepository;
	}

	public interface Callback {
		void onSuccess(DishRetrofitDTO dishRetrofitDTO);
		void onError(Throwable throwable);
	}

	public void execute(Callback callback) {
		dishRetrofitRepository.getRandomDish(new DishRetrofitRepository.Callback() {
			@Override
			public void onSuccess(DishRetrofitDTO dishRetrofitDTO) {
				callback.onSuccess(dishRetrofitDTO);
			}

			@Override
			public void onError(Throwable throwable) {
				callback.onError(throwable);
			}
		});
	}

}
