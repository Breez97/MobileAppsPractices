package ru.mirea.shamrov.domain.repository;

import ru.mirea.shamrov.domain.models.Movie;

public interface MovieRepository {

	boolean saveMovie(Movie movie);
	Movie getMovie();

}
