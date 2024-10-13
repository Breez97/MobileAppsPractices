package ru.mirea.shamrov.domain.usecases.dishes;

import java.util.List;

import ru.mirea.shamrov.domain.models.DishDTO;
import ru.mirea.shamrov.domain.repository.DishRepository;

public class GetDishesByIdUseCase {

	private final DishRepository dishRepository;

	public GetDishesByIdUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public List<DishDTO> execute(List<Integer> ids) {
		return dishRepository.getDishesById(ids);
	}

}
