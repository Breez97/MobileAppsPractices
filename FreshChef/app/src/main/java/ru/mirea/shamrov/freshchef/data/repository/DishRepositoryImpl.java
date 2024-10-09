package ru.mirea.shamrov.freshchef.data.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.shamrov.freshchef.data.storage.DishStorage;
import ru.mirea.shamrov.freshchef.data.storage.models.Dish;
import ru.mirea.shamrov.freshchef.domain.models.DishDTO;
import ru.mirea.shamrov.freshchef.domain.repository.DishRepository;

public class DishRepositoryImpl implements DishRepository {

	private final DishStorage dishStorage;

	public DishRepositoryImpl(DishStorage dishStorage) {
		this.dishStorage = dishStorage;
	}

	@Override
	public boolean addNewDish(DishDTO dish) {
		return dishStorage.addNewDish(mapToDish(dish));
	}

	@Override
	public List<DishDTO> getDishesByTitle(String title) {
		List<Dish> resultDishes = dishStorage.getDishesByTitle(title);
		return resultDishes.stream()
				.map(dish -> mapToDishDTO(dish))
				.collect(Collectors.toList());
	}

	@Override
	public List<DishDTO> getDishesById(List<Integer> ids) {
		List<Dish> resultDishes = dishStorage.getDishesById(ids);
		return resultDishes.stream()
				.map(dish -> mapToDishDTO(dish))
				.collect(Collectors.toList());
	}

	@Override
	public List<DishDTO> getAllDishes() {
		List<Dish> resultDishes = dishStorage.getAllDishes();
		return resultDishes.stream()
				.map(dish -> mapToDishDTO(dish))
				.collect(Collectors.toList());
	}

	private Dish mapToDish(DishDTO dish) {
		return new Dish(dish.getId(), dish.getTitle(), dish.getPrice(), LocalDate.now());
	}

	private DishDTO mapToDishDTO(Dish dish) {
		return new DishDTO(dish.getId(), dish.getTitle(), dish.getPrice());
	}

}
