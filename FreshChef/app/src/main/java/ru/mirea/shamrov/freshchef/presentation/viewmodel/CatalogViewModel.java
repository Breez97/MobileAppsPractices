package ru.mirea.shamrov.freshchef.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.mirea.shamrov.domain.models.DishDTO;
import ru.mirea.shamrov.domain.repository.DishRepository;

public class CatalogViewModel extends ViewModel {

	private final DishRepository dishRepository;

	private final MutableLiveData<List<DishDTO>> items = new MutableLiveData<>();

	public CatalogViewModel(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
		List<DishDTO> itemDishes = dishRepository.getAllDishes();
		items.setValue(itemDishes);
	}

	public LiveData<List<DishDTO>> getItems() {
		return items;
	}
}
