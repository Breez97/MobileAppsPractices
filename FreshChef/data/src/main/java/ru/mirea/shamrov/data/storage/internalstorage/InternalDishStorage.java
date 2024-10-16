package ru.mirea.shamrov.data.storage.internalstorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.shamrov.data.storage.models.DishStorage;

public class InternalDishStorage implements ru.mirea.shamrov.data.storage.DishStorage {

	private final List<DishStorage> DISHES = new ArrayList<>(
			List.of(
					new DishStorage(1, "Grilled Chicken Salad", 9.99, LocalDate.now()),
					new DishStorage(2, "Avocado Toast with Egg", 6.50, LocalDate.now())
			)
	);

	@Override
	public boolean addNewDish(DishStorage dish) {
		for (DishStorage currentDish : DISHES) {
			if (currentDish.getTitle().equalsIgnoreCase(dish.getTitle())) {
				return false;
			}
		}
		dish.setId(DISHES.size() + 1);
		return DISHES.add(dish);
	}

	@Override
	public List<DishStorage> getDishesByTitle(String title) {
		if (title.isEmpty()) {
			return new ArrayList<>();
		}
		return DISHES.stream()
				.filter(dish -> dish.getTitle().toLowerCase().contains(title.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<DishStorage> getDishesById(List<Integer> ids) {
		return DISHES.stream()
				.filter(dish -> ids.contains(dish.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public List<DishStorage> getAllDishes() {
		return DISHES;
	}

}
