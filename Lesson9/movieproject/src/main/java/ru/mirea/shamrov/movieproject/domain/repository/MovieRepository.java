package ru.mirea.shamrov.movieproject.domain.repository;

import ru.mirea.shamrov.movieproject.domain.models.Movie;

public interface MovieRepository {

	boolean saveMovie(Movie movie);
	Movie getMovie();

}
