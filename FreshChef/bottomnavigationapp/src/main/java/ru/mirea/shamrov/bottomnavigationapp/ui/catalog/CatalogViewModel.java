package ru.mirea.shamrov.bottomnavigationapp.ui.catalog;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.shamrov.domain.models.DishRetrofitDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;
import ru.mirea.shamrov.domain.repository.DishRetrofitRepository;

public class CatalogViewModel extends ViewModel {

	private static final Integer DISHES_TO_LOAD = 10;
	private final List<DishRetrofitDTO> currentDishes = new ArrayList<>();
	private int loadedDishesCount = 0;
	private final Context context;

	private final MutableLiveData<List<DishRetrofitDTO>> items = new MutableLiveData<>();
	private final DishRetrofitRepository dishRetrofitRepository;
	private final DishDatabaseRepository dishDatabaseRepository;

	public CatalogViewModel(Context context, DishRetrofitRepository dishRetrofitRepository, DishDatabaseRepository dishDatabaseRepository) {
		this.dishRetrofitRepository = dishRetrofitRepository;
		this.dishDatabaseRepository = dishDatabaseRepository;
		this.context = context;
		for (int i = 0; i < DISHES_TO_LOAD; i++) {
			loadRandomDish();
		}
	}

	private void loadRandomDish() {
		dishRetrofitRepository.getRandomDish(new DishRetrofitRepository.Callback() {
			@Override
			public void onSuccess(DishRetrofitDTO dishRetrofitDTO) {
				currentDishes.add(dishRetrofitDTO);
				loadedDishesCount++;
				if (loadedDishesCount == DISHES_TO_LOAD) {
					items.setValue(new ArrayList<>(currentDishes));
				}
			}

			@Override
			public void onError(Throwable throwable) {
				Toast.makeText(context, "Ошибка загрузки блюда", Toast.LENGTH_SHORT).show();
			}
		});
	}

	public LiveData<List<DishRetrofitDTO>> getItems() {
		return items;
	}

}
