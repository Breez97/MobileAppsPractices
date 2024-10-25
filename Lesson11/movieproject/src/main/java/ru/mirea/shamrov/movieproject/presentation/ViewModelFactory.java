package ru.mirea.shamrov.movieproject.presentation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.data.repository.MovieRepositoryImpl;
import ru.mirea.shamrov.data.storage.MovieStorage;
import ru.mirea.shamrov.data.storage.sharedprefs.SharedPrefMovieStorage;
import ru.mirea.shamrov.domain.repository.MovieRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

	private final Context context;

	public ViewModelFactory(Context context) {
		this.context = context;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		MovieStorage sharedPrefMovieStorage = new SharedPrefMovieStorage(context);
		MovieRepository movieRepository = new MovieRepositoryImpl(sharedPrefMovieStorage);
		return (T) new MainViewModel(movieRepository);
	}

}
