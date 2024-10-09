package ru.mirea.shamrov.freshchef.domain.repository;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.DishDTO;

public interface DishRepository {

	boolean addNewDish(DishDTO dish);
	List<DishDTO> getAllDishes();
	List<DishDTO> getDishesByTitle(String title);
	List<DishDTO> getDishesById(List<Integer> ids);

}
