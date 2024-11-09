package ru.mirea.shamrov.data.storage.internalstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.shamrov.data.storage.models.DishStorage;

public class InternalDishStorage implements ru.mirea.shamrov.data.storage.DishStorage {

	private final List<DishStorage> DISHES = new ArrayList<>(
			List.of(
					new DishStorage(1, "burgers_image", "Вегетерианские бургеры",
							"с майонезом Карри и картофелем фри", 499.0, 300),
					new DishStorage(2, "tacos_image", "Тако с манго",
							"с обжаренным луком и авокадо", 349.0, 175),
					new DishStorage(3, "pasta_image", "Макароны Мафальдин",
							"с кремом из кешью с чесноком", 469.0, 240),
					new DishStorage(4, "burgers_image", "Вегетерианские бургеры",
							"с майонезом Карри и картофелем фри", 499.0, 300),
					new DishStorage(5, "tacos_image", "Тако с манго",
							"с обжаренным луком и авокадо", 349.0, 175),
					new DishStorage(6, "pasta_image", "Макароны Мафальдин",
							"с кремом из кешью с чесноком", 469.0, 240),
					new DishStorage(7, "burgers_image", "Вегетерианские бургеры",
							"с майонезом Карри и картофелем фри", 499.0, 300),
					new DishStorage(8, "tacos_image", "Тако с манго",
							"с обжаренным луком и авокадо", 349.0, 175),
					new DishStorage(9, "pasta_image", "Макароны Мафальдин",
							"с кремом из кешью с чесноком", 469.0, 240)
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
