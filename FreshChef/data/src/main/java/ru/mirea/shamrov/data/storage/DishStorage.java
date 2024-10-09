package ru.mirea.shamrov.data.storage;

import java.util.List;

import ru.mirea.shamrov.data.storage.models.Dish;

public interface DishStorage {

	boolean addNewDish(Dish dish);
	List<Dish> getDishesByTitle(String title);
	List<Dish> getDishesById(List<Integer> ids);
	List<Dish> getAllDishes();

}
