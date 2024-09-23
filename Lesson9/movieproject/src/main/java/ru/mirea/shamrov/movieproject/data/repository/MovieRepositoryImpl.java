package ru.mirea.shamrov.movieproject.data.repository;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import ru.mirea.shamrov.movieproject.domain.models.Movie;
import ru.mirea.shamrov.movieproject.domain.repository.MovieRepository;

public class MovieRepositoryImpl extends ContextWrapper implements MovieRepository {

	private SharedPreferences sharedPreferences;

	public MovieRepositoryImpl(Context base) {
		super(base);
	}

	@Override
	public boolean saveMovie(Movie movie) {
		sharedPreferences = getSharedPreferences("favorite_films",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt("FILM_ID", movie.getId());
		editor.putString("FILM_TITLE", movie.getName());
		editor.apply();
		return true;
	}

	@Override
	public Movie getMovie() {
		sharedPreferences = getSharedPreferences("favorite_films",Context.MODE_PRIVATE);
		return new Movie(sharedPreferences.getInt("FILM_ID", 1),
				sharedPreferences.getString("FILM_TITLE", "Not added"));
	}

}
