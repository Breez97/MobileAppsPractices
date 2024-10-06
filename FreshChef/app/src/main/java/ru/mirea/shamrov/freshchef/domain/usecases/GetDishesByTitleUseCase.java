package ru.mirea.shamrov.freshchef.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.Dish;
import ru.mirea.shamrov.freshchef.domain.repository.DishRepository;

public class GetDishesByTitleUseCase {

	private DishRepository dishRepository;

	public GetDishesByTitleUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public List<Dish> execute(String title) {
		return dishRepository.getDishesByTitle(title);
	}

}