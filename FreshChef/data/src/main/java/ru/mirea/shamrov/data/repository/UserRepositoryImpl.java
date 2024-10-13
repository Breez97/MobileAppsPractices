package ru.mirea.shamrov.data.repository;

import ru.mirea.shamrov.data.storage.UserStorage;
import ru.mirea.shamrov.data.storage.models.User;
import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

	private final UserStorage userStorage;

	public UserRepositoryImpl(UserStorage userStorage) {
		this.userStorage = userStorage;
	}

	@Override
	public boolean saveNewUser(UserDTO userDTO) {
		return userStorage.saveNewUser(mapToUser(userDTO));
	}

	@Override
	public UserDTO getCurrentUser() {
		return maptoUserDTO(userStorage.getCurrentUser());
	}

	private User mapToUser(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getFavoriteDishes());
	}

	private UserDTO maptoUserDTO(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getFavoriteDishes());
	}

}
