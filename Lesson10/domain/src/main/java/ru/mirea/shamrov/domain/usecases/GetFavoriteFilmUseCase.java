package ru.mirea.shamrov.domain.usecases;

import ru.mirea.shamrov.domain.models.Movie;
import ru.mirea.shamrov.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {

	private MovieRepository movieRepository;

	public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Movie execute() {
		return movieRepository.getMovie();
	}

}
