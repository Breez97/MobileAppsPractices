package ru.mirea.shamrov.freshchef.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.Dish;
import ru.mirea.shamrov.freshchef.domain.repository.DishRepository;

public class GetAllDishesUseCase {

	private DishRepository dishRepository;

	public GetAllDishesUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public List<Dish> execute() {
		return dishRepository.getAllDishes();
	}

}
