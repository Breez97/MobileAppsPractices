package ru.mirea.shamrov.bottomnavigationapp.ui.catalog;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.data.repository.DishDatabaseRepositoryImpl;
import ru.mirea.shamrov.data.repository.DishRetrofitImpl;
import ru.mirea.shamrov.data.retrofit.ApiDataSource;
import ru.mirea.shamrov.data.retrofit.datasource.ApiDataSourceRetrofit;
import ru.mirea.shamrov.data.roomdatabase.DishDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.databasestorage.DishRoomDatabaseStorage;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;
import ru.mirea.shamrov.domain.repository.DishRetrofitRepository;

public class CatalogViewModelFactory implements ViewModelProvider.Factory {

	private final Context context;

	public CatalogViewModelFactory(Context context) {
		this.context = context;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		ApiDataSource apiDataSource = new ApiDataSourceRetrofit();
		DishRetrofitRepository dishRetrofitRepository = new DishRetrofitImpl(apiDataSource);
		DishDatabaseStorage dishDatabaseStorage = new DishRoomDatabaseStorage(context);
		DishDatabaseRepository dishDatabaseRepository = new DishDatabaseRepositoryImpl(dishDatabaseStorage);
		return (T) new CatalogViewModel(context, dishRetrofitRepository, dishDatabaseRepository);
	}

}
