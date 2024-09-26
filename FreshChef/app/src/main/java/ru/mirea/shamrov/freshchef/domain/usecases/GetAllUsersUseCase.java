package ru.mirea.shamrov.freshchef.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.User;
import ru.mirea.shamrov.freshchef.domain.repository.UserRepository;

public class GetAllUsersUseCase {

	private UserRepository userRepository;

	public GetAllUsersUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> execute() {
		return userRepository.getAllUsers();
	}
}
