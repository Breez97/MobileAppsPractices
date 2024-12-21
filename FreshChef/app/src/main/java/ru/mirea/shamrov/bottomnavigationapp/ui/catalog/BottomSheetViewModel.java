package ru.mirea.shamrov.bottomnavigationapp.ui.catalog;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ru.mirea.shamrov.domain.models.DishRetrofitDTO;
import ru.mirea.shamrov.domain.repository.DishRetrofitRepository;

public class BottomSheetViewModel extends ViewModel {
	private final Context context;
	private final MutableLiveData<DishRetrofitDTO> currentDish = new MutableLiveData<>();
	private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
	private final DishRetrofitRepository dishRetrofitRepository;

	public BottomSheetViewModel(Context context, DishRetrofitRepository dishRetrofitRepository) {
		this.dishRetrofitRepository = dishRetrofitRepository;
		this.context = context;
	}

	public void loadDishInfo(Integer id) {
		isLoading.setValue(true);
		dishRetrofitRepository.getDishById(id, new DishRetrofitRepository.Callback() {
			@Override
			public void onSuccess(DishRetrofitDTO dish) {
				Log.d(BottomSheetViewModel.class.getSimpleName(), id.toString());
				currentDish.postValue(dish);
				isLoading.postValue(false);
			}

			@Override
			public void onError(Throwable throwable) {
				Log.d(BottomSheetViewModel.class.getSimpleName(), throwable.getMessage());
				isLoading.postValue(false);
			}
		});
	}

	public LiveData<DishRetrofitDTO> getCurrentDish() {
		return currentDish;
	}

	public LiveData<Boolean> getIsLoading() {
		return isLoading;
	}

}