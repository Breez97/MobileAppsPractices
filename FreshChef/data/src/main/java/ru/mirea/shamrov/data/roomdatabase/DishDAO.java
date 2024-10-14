package ru.mirea.shamrov.data.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.mirea.shamrov.data.roomdatabase.model.Dish;

@Dao
public interface DishDAO {

	@Insert
	void addDish(Dish dish);

	@Update
	void updateDish(Dish dish);

	@Query("delete from dishes where title==:title")
	void deleteDish(String title);

	@Query("select * from dishes")
	List<Dish> getAllDishes();

	@Query("select * from dishes where id==:dishId")
	Dish getDish(Integer dishId);

	@Query("delete from dishes")
	void clearTable();

}
