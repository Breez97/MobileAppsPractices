package ru.mirea.shamrov.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.UserRepository;

public class GetAllUsersUseCase {

	private UserRepository userRepository;

	public GetAllUsersUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserDTO> execute() {
		return userRepository.getAllUsers();
	}
}
