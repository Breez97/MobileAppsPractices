package ru.mirea.shamrovio.mireaproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

	private final MutableLiveData<String> mText;

	public HomeViewModel() {
		mText = new MutableLiveData<>();
		mText.setValue("Главная страница");
	}

	public LiveData<String> getText() {
		return mText;
	}
}