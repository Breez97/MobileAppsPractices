package ru.mirea.shamrov.domain.usecases.roomdatabase;

import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;

public class AddNewDishDatabaseUseCase {

	private final DishDatabaseRepository dishDatabaseRepository;

	public AddNewDishDatabaseUseCase(DishDatabaseRepository dishDatabaseRepository) {
		this.dishDatabaseRepository = dishDatabaseRepository;
	}

	public void execute(DishDatabaseDTO dishDatabaseDTO) {
		dishDatabaseRepository.addNewDish(dishDatabaseDTO);
	}

}
