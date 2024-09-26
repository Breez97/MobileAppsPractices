package ru.mirea.shamrov.freshchef.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.shamrov.freshchef.domain.models.Dish;
import ru.mirea.shamrov.freshchef.domain.repository.DishRepository;

public class DishRepositoryImpl implements DishRepository {

	private final List<Dish> DISHES = new ArrayList<>(
			List.of(
					new Dish(1, "Grilled Chicken Salad", 9.99),
					new Dish(2, "Avocado Toast with Egg", 6.50)
			)
	);

	@Override
	public boolean addNewDish(Dish dish) {
		for (Dish currentDish : DISHES) {
			if (currentDish.getTitle().equalsIgnoreCase(dish.getTitle())) {
				return false;
			}
		}
		dish.setId(DISHES.size() + 1);
		return DISHES.add(dish);
	}

	@Override
	public List<Dish> getDishesByTitle(String title) {
		if (title.isEmpty()) {
			return new ArrayList<>();
		}
		return DISHES.stream()
				.filter(dish -> dish.getTitle().toLowerCase().contains(title.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Dish> getDishesById(List<Integer> ids) {
		return DISHES.stream()
				.filter(dish -> ids.contains(dish.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Dish> getAllDishes() {
		return DISHES;
	}

}
