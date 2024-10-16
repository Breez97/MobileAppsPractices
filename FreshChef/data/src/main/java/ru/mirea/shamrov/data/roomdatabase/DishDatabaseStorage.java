package ru.mirea.shamrov.data.roomdatabase;

import java.util.List;

import ru.mirea.shamrov.data.roomdatabase.model.DishDatabase;
import ru.mirea.shamrov.data.roomdatabase.utils.RoomDatabaseCallback;

public interface DishDatabaseStorage {

	void addNewDish(DishDatabase dish);
	void deleteDish(String title);
	void getAllDishes(RoomDatabaseCallback<List<DishDatabase>> callback);

}
