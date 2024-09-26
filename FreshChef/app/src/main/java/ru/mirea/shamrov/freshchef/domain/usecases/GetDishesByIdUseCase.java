package ru.mirea.shamrov.freshchef.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.Dish;
import ru.mirea.shamrov.freshchef.domain.repository.DishRepository;

public class GetDishesByIdUseCase {

	private DishRepository dishRepository;

	public GetDishesByIdUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public List<Dish> execute(List<Integer> ids) {
		return dishRepository.getDishesById(ids);
	}

}
