package ru.mirea.shamrov.freshchef.presentation.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.data.api.ApiNetwork;
import ru.mirea.shamrov.data.api.apinetwork.ApiNetworkSignal;
import ru.mirea.shamrov.data.repository.DishDatabaseRepositoryImpl;
import ru.mirea.shamrov.data.roomdatabase.DishDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.databasestorage.DishRoomDatabaseStorage;

public class MainViewModelFactory implements ViewModelProvider.Factory {

	private final Context context;

	public MainViewModelFactory(Context context) {
		this.context = context;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		ApiNetwork apiNetwork = new ApiNetworkSignal();
		DishDatabaseStorage dishDatabaseStorage = new DishRoomDatabaseStorage(context);
		DishDatabaseRepositoryImpl dishDatabaseRepository = new DishDatabaseRepositoryImpl(dishDatabaseStorage, apiNetwork);
		return (T) new MainViewModel(dishDatabaseRepository);
	}
}
