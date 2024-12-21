package ru.mirea.shamrov.domain.repository;

import java.util.List;

import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public interface DishDatabaseRepository {

	void addNewDish(DishDatabaseDTO dishDatabaseDTO);
	void deleteDish(String title);
	void getAllDishes(DatabaseCallback<List<DishDatabaseDTO>> callback);
//	DishDatabaseDTO getDishFromApi();

}
