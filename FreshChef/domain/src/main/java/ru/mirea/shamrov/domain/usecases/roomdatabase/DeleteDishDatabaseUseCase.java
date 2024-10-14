package ru.mirea.shamrov.domain.usecases.roomdatabase;

import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;

public class DeleteDishDatabaseUseCase {

	private final DishDatabaseRepository dishDatabaseRepository;

	public DeleteDishDatabaseUseCase(DishDatabaseRepository dishDatabaseRepository) {
		this.dishDatabaseRepository = dishDatabaseRepository;
	}

	public void execute(String title) {
		dishDatabaseRepository.deleteDish(title);
	}

}
