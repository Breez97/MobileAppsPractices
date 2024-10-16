package ru.mirea.shamrov.data.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.shamrov.data.storage.models.DishStorage;
import ru.mirea.shamrov.domain.models.DishDTO;
import ru.mirea.shamrov.domain.repository.DishRepository;

public class DishRepositoryImpl implements DishRepository {

	private final ru.mirea.shamrov.data.storage.DishStorage dishStorage;

	public DishRepositoryImpl(ru.mirea.shamrov.data.storage.DishStorage dishStorage) {
		this.dishStorage = dishStorage;
	}

	@Override
	public boolean addNewDish(DishDTO dish) {
		return dishStorage.addNewDish(mapToDish(dish));
	}

	@Override
	public List<DishDTO> getDishesByTitle(String title) {
		List<DishStorage> resultDishes = dishStorage.getDishesByTitle(title);
		return resultDishes.stream()
				.map(dish -> mapToDishDTO(dish))
				.collect(Collectors.toList());
	}

	@Override
	public List<DishDTO> getDishesById(List<Integer> ids) {
		List<DishStorage> resultDishes = dishStorage.getDishesById(ids);
		return resultDishes.stream()
				.map(dish -> mapToDishDTO(dish))
				.collect(Collectors.toList());
	}

	@Override
	public List<DishDTO> getAllDishes() {
		List<DishStorage> resultDishes = dishStorage.getAllDishes();
		return resultDishes.stream()
				.map(dish -> mapToDishDTO(dish))
				.collect(Collectors.toList());
	}

	private DishStorage mapToDish(DishDTO dish) {
		return new DishStorage(dish.getId(), dish.getTitle(), dish.getPrice(), LocalDate.now());
	}

	private DishDTO mapToDishDTO(DishStorage dish) {
		return new DishDTO(dish.getId(), dish.getTitle(), dish.getPrice());
	}

}
