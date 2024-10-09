package ru.mirea.shamrov.freshchef.domain.repository;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.UserDTO;

public interface UserRepository {

	List<UserDTO> getAllUsers();
	List<Integer> getUserFavoriteDishes(String name);

}
