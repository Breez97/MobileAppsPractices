package ru.mirea.shamrov.data.roomdatabase;

import java.util.List;

import ru.mirea.shamrov.data.roomdatabase.utils.RoomDatabaseCallback;
import ru.mirea.shamrov.data.roomdatabase.model.Dish;

public interface DishDatabaseStorage {

	void addNewDish(Dish dish);
	void deleteDish(String title);
	void getAllDishes(RoomDatabaseCallback<List<Dish>> callback);

}
