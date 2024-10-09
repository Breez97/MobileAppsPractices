package ru.mirea.shamrov.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.domain.models.DishDTO;
import ru.mirea.shamrov.domain.repository.DishRepository;

public class GetDishesByIdUseCase {

	private DishRepository dishRepository;

	public GetDishesByIdUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public List<DishDTO> execute(List<Integer> ids) {
		return dishRepository.getDishesById(ids);
	}

}
