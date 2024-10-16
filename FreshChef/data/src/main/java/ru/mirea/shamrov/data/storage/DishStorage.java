package ru.mirea.shamrov.data.storage;

import java.util.List;

public interface DishStorage {

	boolean addNewDish(ru.mirea.shamrov.data.storage.models.DishStorage dish);
	List<ru.mirea.shamrov.data.storage.models.DishStorage> getDishesByTitle(String title);
	List<ru.mirea.shamrov.data.storage.models.DishStorage> getDishesById(List<Integer> ids);
	List<ru.mirea.shamrov.data.storage.models.DishStorage> getAllDishes();

}
