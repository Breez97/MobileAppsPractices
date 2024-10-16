package ru.mirea.shamrov.data.repository;

import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.shamrov.data.api.ApiNetwork;
import ru.mirea.shamrov.data.api.model.DishApi;
import ru.mirea.shamrov.data.roomdatabase.DishDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.model.DishDatabase;
import ru.mirea.shamrov.data.roomdatabase.utils.RoomDatabaseCallback;
import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public class DishDatabaseRepositoryImpl implements DishDatabaseRepository {

	private final DishDatabaseStorage dishDatabaseStorage;
	private final ApiNetwork apiNetwork;

	public DishDatabaseRepositoryImpl(DishDatabaseStorage dishDatabaseStorage, ApiNetwork apiNetwork) {
		this.dishDatabaseStorage = dishDatabaseStorage;
		this.apiNetwork = apiNetwork;
	}

	@Override
	public void addNewDish(DishDatabaseDTO dishDTO) {
		dishDatabaseStorage.addNewDish(mapDishDatabaseDTOToDishDatabase(dishDTO));
	}

	@Override
	public void deleteDish(String title) {
		dishDatabaseStorage.deleteDish(title);
	}

	@Override
	public void getAllDishes(DatabaseCallback<List<DishDatabaseDTO>> callback) {
		dishDatabaseStorage.getAllDishes(new RoomDatabaseCallback<List<DishDatabase>>() {
			@Override
			public void onSuccess(List<DishDatabase> data) {
				List<DishDatabaseDTO> dishesDTO = data.stream()
						.map(DishDatabaseRepositoryImpl.this::mapDishDatabaseToDishDatabaseDTO)
						.collect(Collectors.toList());
				callback.onSuccess(dishesDTO);
			}

			@Override
			public void onError(Exception e) {
				callback.onError(e);
			}
		});
	}

	@Override
	public DishDatabaseDTO getDishFromApi() {
		return mapDishApiToDishDatabaseDTO(apiNetwork.getRandomDish());
	}

	private DishDatabase mapDishDatabaseDTOToDishDatabase(DishDatabaseDTO dish) {
		return new DishDatabase(dish.getTitle(), dish.getPrice());
	}

	public DishDatabaseDTO mapDishApiToDishDatabaseDTO(DishApi dishApi) {
		if (dishApi == null || dishApi.getTitle() == null) {
			return new DishDatabaseDTO("Unknown", 0.0);
		}
		return new DishDatabaseDTO(dishApi.getTitle(), dishApi.getPrice());
	}

	private DishDatabaseDTO mapDishDatabaseToDishDatabaseDTO(DishDatabase dish) {
		return new DishDatabaseDTO(dish.getTitle(), dish.getPrice());
	}
}
