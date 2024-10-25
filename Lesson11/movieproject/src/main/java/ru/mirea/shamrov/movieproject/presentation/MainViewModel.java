package ru.mirea.shamrov.movieproject.presentation;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.shamrov.domain.models.Movie;
import ru.mirea.shamrov.domain.repository.MovieRepository;
import ru.mirea.shamrov.domain.usecases.GetFavoriteMovieUseCase;
import ru.mirea.shamrov.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainViewModel extends ViewModel {

	private final MovieRepository movieRepository;
	private MutableLiveData<String> favoriteMovie = new MutableLiveData<>();

	public MainViewModel(MovieRepository movieRepository) {
		Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModelCreated");
		this.movieRepository = movieRepository;
	}

	@Override
	protected void onCleared() {
		Log.d(MainViewModel.class.getSimpleName().toString(), "MainViewModelCleared");
		super.onCleared();
	}

	public MutableLiveData<String> getFavoriteMovie() {
		return favoriteMovie;
	}

	public void setText(Movie movie) {
		boolean result = new SaveMovieToFavoriteUseCase(movieRepository).execute(movie);
		favoriteMovie.setValue(Boolean.toString(result));
	}

	public void getText() {
		Movie movie = new GetFavoriteMovieUseCase(movieRepository).execute();
		favoriteMovie.setValue(String.format("My favorite movie is %s", movie.getName()));
	}

}
