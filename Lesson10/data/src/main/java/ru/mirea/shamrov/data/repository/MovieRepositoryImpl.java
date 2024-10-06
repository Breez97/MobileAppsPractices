package ru.mirea.shamrov.data.repository;

import java.time.LocalDate;

import ru.mirea.shamrov.data.storage.MovieStorage;
import ru.mirea.shamrov.data.storage.models.Movie;
import ru.mirea.shamrov.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {

	private final MovieStorage movieStorage;

	public MovieRepositoryImpl(MovieStorage sharedPrefMovieStorage) {
		this.movieStorage = sharedPrefMovieStorage;
	}

	@Override
	public boolean saveMovie(ru.mirea.shamrov.domain.models.Movie movie) {
		movieStorage.save(mapToStorage(movie));
		return true;
	}

	@Override
	public ru.mirea.shamrov.domain.models.Movie getMovie() {
		Movie movie = movieStorage.get();
		return mapToDomain(movie);
	}

	private Movie mapToStorage(ru.mirea.shamrov.domain.models.Movie movie) {
		String name = movie.getName();
		return new Movie(2, name, LocalDate.now().toString());
	}

	private ru.mirea.shamrov.domain.models.Movie mapToDomain(Movie movie) {
		String name = movie.getName();
		return new ru.mirea.shamrov.domain.models.Movie(movie.getId(), name);
	}

}
