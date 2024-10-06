package ru.mirea.shamrov.domain.usecases;

import ru.mirea.shamrov.domain.models.Movie;
import ru.mirea.shamrov.domain.repository.MovieRepository;

public class SaveFilmToFavoriteUseCase {

	private MovieRepository movieRepository;

	public SaveFilmToFavoriteUseCase(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public boolean execute(Movie movie) {
		return movieRepository.saveMovie(movie);
	}

}
