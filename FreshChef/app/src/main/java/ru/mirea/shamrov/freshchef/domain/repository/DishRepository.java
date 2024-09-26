package ru.mirea.shamrov.freshchef.domain.repository;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.Dish;

public interface DishRepository {

	boolean addNewDish(Dish dish);
	List<Dish> getAllDishes();
	List<Dish> getDishesByTitle(String title);
	List<Dish> getDishesById(List<Integer> ids);

}
