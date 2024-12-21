package ru.mirea.shamrov.data.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.mirea.shamrov.data.roomdatabase.dao.UserDAO;

@Database(entities = {ru.mirea.shamrov.data.roomdatabase.model.UserDatabase.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

	public abstract UserDAO getUserDAO();

}
