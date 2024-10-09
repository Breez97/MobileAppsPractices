package ru.mirea.shamrov.data.storage;

import java.util.List;

import ru.mirea.shamrov.data.storage.models.User;

public interface UserStorage {

	List<User> getAllUsers();
	List<Integer> getUserFavoriteDishes(String name);

}
