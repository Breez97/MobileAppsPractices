package ru.mirea.shamrov.data.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.mirea.shamrov.data.roomdatabase.model.DishDatabase;

@Dao
public interface DishDAO {

	@Insert
	void addDish(DishDatabase dish);

	@Update
	void updateDish(DishDatabase dish);

	@Query("delete from dishes where title==:title")
	void deleteDish(String title);

	@Query("select * from dishes")
	List<DishDatabase> getAllDishes();

	@Query("select * from dishes where id==:dishId")
	DishDatabase getDish(Integer dishId);

	@Query("delete from dishes")
	void clearTable();

}
