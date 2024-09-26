package ru.mirea.shamrov.freshchef.domain.repository;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.User;

public interface UserRepository {

	List<User> getAllUsers();
	List<Integer> getUserFavoriteDishes(String name);

}
