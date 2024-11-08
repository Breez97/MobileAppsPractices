package ru.mirea.shamrov.freshchef.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.data.repository.DishRepositoryImpl;
import ru.mirea.shamrov.data.storage.DishStorage;
import ru.mirea.shamrov.data.storage.internalstorage.InternalDishStorage;
import ru.mirea.shamrov.domain.repository.DishRepository;

public class CatalogViewModelFactory implements ViewModelProvider.Factory {

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		DishStorage dishStorage = new InternalDishStorage();
		DishRepository dishRepository = new DishRepositoryImpl(dishStorage);
		return (T) new CatalogViewModel(dishRepository);
	}

}
