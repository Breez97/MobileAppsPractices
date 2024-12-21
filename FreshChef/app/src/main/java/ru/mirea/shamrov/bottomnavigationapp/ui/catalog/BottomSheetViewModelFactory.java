package ru.mirea.shamrov.bottomnavigationapp.ui.catalog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import ru.mirea.shamrov.data.repository.DishRetrofitImpl;
import ru.mirea.shamrov.data.retrofit.ApiDataSource;
import ru.mirea.shamrov.data.retrofit.datasource.ApiDataSourceRetrofit;
import ru.mirea.shamrov.domain.repository.DishRetrofitRepository;

public class BottomSheetViewModelFactory implements ViewModelProvider.Factory {
	private final Context context;

	public BottomSheetViewModelFactory(Context context) {
		this.context = context;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		ApiDataSource apiDataSource = new ApiDataSourceRetrofit();
		DishRetrofitRepository dishRetrofitRepository = new DishRetrofitImpl(apiDataSource);
		return (T) new BottomSheetViewModel(context, dishRetrofitRepository);
	}
}