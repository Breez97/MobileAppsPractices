package ru.mirea.shamrov.data.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.shamrov.data.roomdatabase.dao.DishDAO;

@Database(entities = {ru.mirea.shamrov.data.roomdatabase.model.DishDatabase.class}, version = 1)
public abstract class DishDatabase extends RoomDatabase {

	public abstract DishDAO getDishDAO();

}
