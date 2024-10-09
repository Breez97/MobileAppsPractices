package ru.mirea.shamrov.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.domain.models.DishDTO;
import ru.mirea.shamrov.domain.repository.DishRepository;

public class GetAllDishesUseCase {

	private DishRepository dishRepository;

	public GetAllDishesUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public List<DishDTO> execute() {
		return dishRepository.getAllDishes();
	}

}
