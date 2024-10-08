package ru.mirea.shamrov.domain.repository;

import java.util.List;

import ru.mirea.shamrov.domain.models.UserDTO;

public interface UserRepository {

	List<UserDTO> getAllUsers();
	List<Integer> getUserFavoriteDishes(String name);

}
