package ru.mirea.shamrov.domain.repository;

import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public interface UserRepository {

	void saveNewUser(UserDTO userDTO);
	void getCurrentUser(String email, DatabaseCallback<UserDTO> callback);
	void updateUserInfoDatabase(UserDTO userDTO);

}
