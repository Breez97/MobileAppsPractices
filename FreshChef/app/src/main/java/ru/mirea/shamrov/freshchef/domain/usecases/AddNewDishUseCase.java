package ru.mirea.shamrov.freshchef.domain.usecases;

import ru.mirea.shamrov.freshchef.domain.models.DishDTO;
import ru.mirea.shamrov.freshchef.domain.repository.DishRepository;

public class AddNewDishUseCase {

	private DishRepository dishRepository;

	public AddNewDishUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public boolean execute(DishDTO dish) {
		return dishRepository.addNewDish(dish);
	}

}
