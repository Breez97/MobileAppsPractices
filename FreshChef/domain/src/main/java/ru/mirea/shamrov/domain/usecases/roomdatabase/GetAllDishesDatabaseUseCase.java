package ru.mirea.shamrov.domain.usecases.roomdatabase;

import java.util.List;

import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public class GetAllDishesDatabaseUseCase {

	private final DishDatabaseRepository dishDatabaseRepository;

	public GetAllDishesDatabaseUseCase(DishDatabaseRepository dishDatabaseRepository) {
		this.dishDatabaseRepository = dishDatabaseRepository;
	}

	public void execute(DatabaseCallback<List<DishDatabaseDTO>> domainCallback) {
		dishDatabaseRepository.getAllDishes(new DatabaseCallback<>() {
			@Override
			public void onSuccess(List<DishDatabaseDTO> dataResult) {
				domainCallback.onSuccess(dataResult);
			}

			@Override
			public void onError(Exception e) {
				domainCallback.onError(e);
			}
		});
	}

}
