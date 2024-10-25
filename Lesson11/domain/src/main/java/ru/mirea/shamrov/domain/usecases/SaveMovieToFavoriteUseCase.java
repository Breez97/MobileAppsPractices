package ru.mirea.shamrov.domain.usecases;

import ru.mirea.shamrov.domain.models.Movie;
import ru.mirea.shamrov.domain.repository.MovieRepository;

public class SaveMovieToFavoriteUseCase {

	private MovieRepository movieRepository;

	public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public boolean execute(Movie movie) {
		return movieRepository.saveMovie(movie);
	}

}
