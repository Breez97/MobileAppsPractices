package ru.mirea.shamrov.freshchef.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;
import ru.mirea.shamrov.domain.usecases.api.GetDishFromApiUseCase;
import ru.mirea.shamrov.domain.usecases.roomdatabase.AddNewDishDatabaseUseCase;
import ru.mirea.shamrov.domain.usecases.roomdatabase.DeleteDishDatabaseUseCase;
import ru.mirea.shamrov.domain.usecases.roomdatabase.GetAllDishesDatabaseUseCase;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public class MainViewModel extends ViewModel {

	private final DishDatabaseRepository dishDatabaseRepository;

	private final MutableLiveData<List<DishDatabaseDTO>> allDishes = new MutableLiveData<>();
	private final MutableLiveData<DishDatabaseDTO> apiDish = new MutableLiveData<>();
	private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
	private final MutableLiveData<String> successMessage = new MutableLiveData<>();
	private final MediatorLiveData<String> formattedData = new MediatorLiveData<>();

	private final AddNewDishDatabaseUseCase addNewDishDatabaseUseCase;
	private final GetAllDishesDatabaseUseCase getAllDishesDatabaseUseCase;
	private final DeleteDishDatabaseUseCase deleteDishDatabaseUseCase;
	private final GetDishFromApiUseCase getDishFromApiUseCase;

	public MainViewModel(DishDatabaseRepository dishDatabaseRepository) {
		this.dishDatabaseRepository = dishDatabaseRepository;
		addNewDishDatabaseUseCase = new AddNewDishDatabaseUseCase(dishDatabaseRepository);
		getAllDishesDatabaseUseCase = new GetAllDishesDatabaseUseCase(dishDatabaseRepository);
		deleteDishDatabaseUseCase = new DeleteDishDatabaseUseCase(dishDatabaseRepository);
		getDishFromApiUseCase = new GetDishFromApiUseCase(dishDatabaseRepository);

		formattedData.addSource(allDishes, allDishes -> {
			updateFormattedDishes(allDishes);
		});
		formattedData.addSource(apiDish, apiDish -> {
			if (apiDish != null) {
				getAllDishes();
			}
		});
	}

	private void updateFormattedDishes(List<DishDatabaseDTO> dishes) {
		if (dishes != null && !dishes.isEmpty()) {
			StringBuilder dishesText = new StringBuilder();
			dishesText.append("List of All Dishes:\n");
			for (DishDatabaseDTO dish : dishes) {
				dishesText.append("-------------------------------------------------------\n")
						.append(dish.toString())
						.append("\n");
			}
			formattedData.setValue(dishesText.toString());
		} else {
			formattedData.setValue("No dishes available");
		}
	}

	public LiveData<String> getFormattedData() {
		return formattedData;
	}

	public LiveData<DishDatabaseDTO> getApiDish() {
		return apiDish;
	}

	public LiveData<String> getErrorMessage() {
		return errorMessage;
	}

	public LiveData<String> getSuccessMessage() {
		return successMessage;
	}

	public void addNewDish(String title, Double price) {
		DishDatabaseDTO dishDatabaseDTO = new DishDatabaseDTO(title, price);
		addNewDishDatabaseUseCase.execute(dishDatabaseDTO);
		successMessage.setValue("New dish with title " + title + " and price " + price + " added");
		getAllDishes();
	}

	public void getAllDishes() {
		getAllDishesDatabaseUseCase.execute(new DatabaseCallback<List<DishDatabaseDTO>>() {
			@Override
			public void onSuccess(List<DishDatabaseDTO> allDishes) {
				MainViewModel.this.allDishes.setValue(allDishes);
			}

			@Override
			public void onError(Exception e) {
				errorMessage.setValue(e.getMessage());
			}
		});
	}

	public void deleteDish(String title) {
		deleteDishDatabaseUseCase.execute(title);
		successMessage.setValue("Dish with title " + title + " deleted");
		getAllDishes();
	}

	public void getDishFromApi() {
		DishDatabaseDTO dishFromApi = getDishFromApiUseCase.execute();
		apiDish.setValue(dishFromApi);
	}
}