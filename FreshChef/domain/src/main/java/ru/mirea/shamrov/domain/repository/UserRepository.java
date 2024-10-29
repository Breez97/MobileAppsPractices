package ru.mirea.shamrov.domain.repository;

import ru.mirea.shamrov.domain.models.UserDTO;

public interface UserRepository {

	boolean saveNewUser(UserDTO userDTO);
	UserDTO getCurrentUser();

}
