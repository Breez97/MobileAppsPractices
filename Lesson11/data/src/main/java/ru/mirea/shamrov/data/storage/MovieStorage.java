package ru.mirea.shamrov.data.storage;

import ru.mirea.shamrov.data.storage.models.Movie;

public interface MovieStorage {

	Movie get();
	boolean save(Movie movie);

}
