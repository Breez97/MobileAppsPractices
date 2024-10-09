package ru.mirea.shamrov.freshchef.data.storage;

import java.util.List;

import ru.mirea.shamrov.freshchef.data.storage.models.User;

public interface UserStorage {

	List<User> getAllUsers();
	List<Integer> getUserFavoriteDishes(String name);

}
