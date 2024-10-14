package ru.mirea.shamrov.data.repository;

import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.shamrov.data.roomdatabase.DishDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.model.Dish;
import ru.mirea.shamrov.data.roomdatabase.utils.RoomDatabaseCallback;
import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public class DishDatabaseRepositoryImpl implements DishDatabaseRepository {

	private final DishDatabaseStorage dishDatabaseStorage;

	public DishDatabaseRepositoryImpl(DishDatabaseStorage dishDatabaseStorage) {
		this.dishDatabaseStorage = dishDatabaseStorage;
	}

	@Override
	public void addNewDish(DishDatabaseDTO dishDTO) {
		dishDatabaseStorage.addNewDish(mapToDish(dishDTO));
	}

	@Override
	public void deleteDish(String title) {
		dishDatabaseStorage.deleteDish(title);
	}

	@Override
	public void getAllDishes(DatabaseCallback<List<DishDatabaseDTO>> callback) {
		dishDatabaseStorage.getAllDishes(new RoomDatabaseCallback<List<Dish>>() {
			@Override
			public void onSuccess(List<Dish> data) {
				List<DishDatabaseDTO> dishesDTO = data.stream()
						.map(DishDatabaseRepositoryImpl.this::mapToDishDatabaseDTO)
						.collect(Collectors.toList());
				callback.onSuccess(dishesDTO);
			}

			@Override
			public void onError(Exception e) {
				callback.onError(e);
			}
		});
	}

	private Dish mapToDish(DishDatabaseDTO dish) {
		return new Dish(dish.getTitle(), dish.getPrice());
	}

	private DishDatabaseDTO mapToDishDatabaseDTO(Dish dish) {
		return new DishDatabaseDTO(dish.getTitle(), dish.getPrice());
	}
}
