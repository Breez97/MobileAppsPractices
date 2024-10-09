package ru.mirea.shamrov.domain.repository;

import java.util.List;

import ru.mirea.shamrov.domain.models.DishDTO;

public interface DishRepository {

	boolean addNewDish(DishDTO dish);
	List<DishDTO> getAllDishes();
	List<DishDTO> getDishesByTitle(String title);
	List<DishDTO> getDishesById(List<Integer> ids);

}
