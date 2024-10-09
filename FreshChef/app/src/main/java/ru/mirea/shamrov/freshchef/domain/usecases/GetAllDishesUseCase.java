package ru.mirea.shamrov.freshchef.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.DishDTO;
import ru.mirea.shamrov.freshchef.domain.repository.DishRepository;

public class GetAllDishesUseCase {

	private DishRepository dishRepository;

	public GetAllDishesUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public List<DishDTO> execute() {
		return dishRepository.getAllDishes();
	}

}
