package ru.mirea.shamrov.domain.usecases;

import ru.mirea.shamrov.domain.models.DishDTO;
import ru.mirea.shamrov.domain.repository.DishRepository;

public class AddNewDishUseCase {

	private DishRepository dishRepository;

	public AddNewDishUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public boolean execute(DishDTO dish) {
		return dishRepository.addNewDish(dish);
	}

}
