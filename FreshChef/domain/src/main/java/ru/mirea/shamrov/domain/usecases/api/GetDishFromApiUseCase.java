package ru.mirea.shamrov.domain.usecases.api;

import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;

public class GetDishFromApiUseCase {

	private final DishDatabaseRepository dishDatabaseRepository;

	public GetDishFromApiUseCase(DishDatabaseRepository dishDatabaseRepository) {
		this.dishDatabaseRepository = dishDatabaseRepository;
	}

	public DishDatabaseDTO execute() {
		return dishDatabaseRepository.getDishFromApi();
	}
}
