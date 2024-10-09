package ru.mirea.shamrov.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.domain.models.DishDTO;
import ru.mirea.shamrov.domain.repository.DishRepository;

public class GetDishesByTitleUseCase {

	private DishRepository dishRepository;

	public GetDishesByTitleUseCase(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}

	public List<DishDTO> execute(String title) {
		return dishRepository.getDishesByTitle(title);
	}

}
