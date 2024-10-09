package ru.mirea.shamrov.data.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<UserDTO> getAllUsers() {
		List<User> resultUsers = userStorage.getAllUsers();
		return resultUsers.stream()
				.map(user -> mapToUserDTO(user))
				.collect(Collectors.toList());
	}

	@Override
	public List<Integer> getUserFavoriteDishes(String name) {
		return userStorage.getUserFavoriteDishes(name);
	}

	private User mapToUser(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getFavoriteDishes(), LocalDate.now());
	}

	private UserDTO mapToUserDTO(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getFavoriteDishes());
	}

}
