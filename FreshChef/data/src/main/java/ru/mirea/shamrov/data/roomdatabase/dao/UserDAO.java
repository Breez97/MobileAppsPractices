package ru.mirea.shamrov.data.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import ru.mirea.shamrov.data.roomdatabase.model.UserDatabase;

@Dao
public interface UserDAO {

	@Insert
	void addUser(UserDatabase user);

	@Query("select * from user_table where email=:email")
	UserDatabase getUser(String email);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertOrUpdateUser(UserDatabase user);

}
